package com.example.LMS.configuration;

import org.aspectj.weaver.ast.Instanceof;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인증 실패 시 실행되는 로직을 구현
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String msg = "로그인에 실패하였습니다.";

        if (exception instanceof InternalAuthenticationServiceException) {
            msg = exception.getMessage();
        }

        // forward 방식으로 실패 페이지로 이동하도록 설정
        setUseForward(true);
        // 인증 실패 시 기본적으로 이동할 URL을 설정
        setDefaultFailureUrl("/member/login?error=true");
        request.setAttribute("errorMessage", msg);

        System.out.println("로그인에 실패하였습니다");
        super.onAuthenticationFailure(request, response, exception);
    }
}
