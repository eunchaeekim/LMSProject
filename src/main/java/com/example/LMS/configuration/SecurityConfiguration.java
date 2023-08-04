package com.example.LMS.configuration;

import com.example.LMS.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    // PasswordEncoder는 스프링 시큐리티에서 사용되는 비밀번호 암호화를 담당하는 인터페이스
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        CSRF는 웹 애플리케이션에서 중요한 작업(예: 계정 삭제, 금융 거래 등)을 수행하는 요청들이 사용자의 의도와 무관하게 악의적인 웹 사이트에서 요청될 수 있는 보안 취약점이다.
        이를 방지하기 위해 Spring Security는 기본적으로 CSRF 보호 기능을 제공한다.
        이 기능은 요청하는 사용자가 실제로 웹 애플리케이션 페이지를 통해 요청을 보낸 것인지를 확인하는 토큰을 사용하여 보호한다.
        그러나 때로는 CSRF 보호 기능을 비활성화해야 하는 경우가 있다.
        예를 들어, 웹 애플리케이션이 API를 제공하고 외부 클라이언트(예: 모바일 앱 또는 다른 웹 사이트)에서 해당 API를 호출해야 하는 경우, CSRF 토큰을 각각의 클라이언트에게 제공하기 어려울 수 있다.
        이럴 때 CSRF 보호를 비활성화하여 외부 클라이언트에서 요청을 보낼 수 있도록 하는 것이 유용하다.
        */
        http.csrf().disable();
        // 요청에 대한 권한 설정 (아래 경로에 대해서는 모든 사용자에게 접근 허용)
        http.authorizeRequests()
                .antMatchers(
                        "/"
                        , "/member/register"
                        , "/member/email-auth"
                        ,"/member/find/password"
                        , "/member/reset/password"
                )
                .permitAll();

        // 요청에 대한 보안 설정
        /*
        http.authorizeRequests()
                        .antMatchers("/admin/**")
                        // 선택한 URL에 대해 "ROLE_ADMIN" 권한을 가진 사용자만 접근할 수 있도록 설정
                        .hasAnyAuthority("ROLE_ADMIN");

         */

        // 폼 기반 로그인 설정을 구성
        http.formLogin()
                // 로그인 페이지의 경로 설정
                .loginPage("/member/login")
                // 로그인 실패 시 동작 지정
                .failureHandler(getFailureHandler())
                // 해당 페이지에 대해 모든 사용자 접근 허용
                .permitAll();

        // 로그아웃 설정
        http.logout()
                // "/member/logout"로 접속하면 로그아웃 처리 진행
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                // 로그아웃이 성공적으로 처리된 후에 사용자를 리다이렉트할 URL을 지정
                .logoutSuccessUrl("/")
                // 로그아웃 시 현재 사용자의 HttpSession을 무효화(invalidate)하는 설정
                // 이 설정을 true로 지정하면, 사용자의 세션 정보가 삭제되어 로그아웃이 완전히 이루어짐
                // 사용자의 세션 정보가 삭제되므로, 로그아웃 후에는 해당 사용자가 세션을 이용한 요청을 할 수 없음
                .invalidateHttpSession(true);

        // 객체에 대한 예외 처리 관련 설정
        http.exceptionHandling()
                // 접근이 거부된 경우(권한이 없는 경우) "/error/denied" 경로로 리다이렉트하도록 설정
                .accessDeniedPage("/error/denied");

        super.configure(http);
    }

    // 사용자 인증 방식을 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 사용자 정보를 데이터베이스에서 가져와 인증
        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }


}
