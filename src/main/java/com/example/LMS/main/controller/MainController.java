package com.example.LMS.main.controller;

import com.example.LMS.component.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index() {
        /*
        String email = "koc0501@naver.com";
        String subject = "제목";
        String text = "<p>본문.</p><p>테스트</p>";
        mailComponents.sendMail(email, subject, text);
         */
        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }
}
