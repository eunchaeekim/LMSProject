package com.example.LMS.member.controller;

import com.example.LMS.member.model.MemberInput;
import com.example.LMS.member.model.ResetPasswordInput;
import com.example.LMS.member.repository.MemberRepository;
import com.example.LMS.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller

public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    /*
    의존성 주입을 위한 코드.
    MemberController가 MemberRepository 객체를 사용해야 한다면, 이 생성자를 통해 MemberRepository를 주입받을 수 있음
     */

    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @GetMapping("/member/register")
    public String register() {
        System.out.println("request get");

        return "member/register";
    }
    /*
    @PostMapping("/member/register")
    public String registerSubmit() {
        System.out.println("request post");

        return "member/register";
    }
     */


    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, MemberInput parameter) {
       boolean result = memberService.register(parameter);
       // //컨트롤러에서 처리한 결과나 계산된 값을 뷰로 전달하여 뷰에서 해당 값을 출력하거나 사용할 수 있도록 함
       // Model 객체는 뷰에 전달할 데이터를 담는 컨테이너 역할을 하며, addAttribute 메서드는 데이터를 Model 객체에 추가하는 역할을 수행.
        // void addAttribute(String attributeName, Object attributeValue)
        // 이후 해당 데이터를 뷰에서 ${result} 형식으로 사용할 수 있음
       model.addAttribute("result",result);


        return "member/register_complete";
    }

    // http://www.naver.com/news/list.do?id=123&key=124&text=쿼리
    // https://
    // 프로토콜://도메인(IP)/news/list.do?쿼리스트링(파라미터)
    // https://www.naver.com/cafe/detail.do?id=1111
    // https://www.naver.com/cafe/detail.do?id=2222


    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result",result);
        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo() {
        return "member/info";
    }

    @RequestMapping("/member/login")
    public String login() {

        return "member/login";
    }

    @GetMapping("/member/find/password")
    public String findPassword(){
        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter) {
        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e) {

        }
        model.addAttribute("result",result);

        return "member/find_password_result";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request){
        // "/member/reset/password?id=XXX"와 같은 URL에서 "id"라는 파라미터 값을 추출
        String uuid = request.getParameter("id");
        boolean result = memberService.checkResetPassword(uuid);
        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter){
        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        } catch(Exception e){

        }
        model.addAttribute("result", result);

        return "member/reset_password_result";

    }







}
