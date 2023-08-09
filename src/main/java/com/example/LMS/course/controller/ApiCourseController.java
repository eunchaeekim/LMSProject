package com.example.LMS.course.controller;

import com.example.LMS.admin.service.CategoryService;
import com.example.LMS.common.ResponseResult;
import com.example.LMS.course.model.ServiceResult;
import com.example.LMS.course.model.TakeCourseInput;
import com.example.LMS.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ApiCourseController extends BaseController {
    private final CourseService courseService;
    private final CategoryService categoryService;

    @PostMapping("api/course/req.api")
    public ResponseEntity<?> courseReq(Model model
            , @RequestBody TakeCourseInput parameter
            , Principal principal){

         // <?>는 임의의 타입을 나타냄

        // Model: 뷰와 컨트롤러 간 데이터 전달을 위한 Spring의 객체

        // @RequestBody: HTTP 요청의 본문(body)에 있는 데이터를 TakeCourseInput 클래스로 변환하여 받음
        // parameter 객체에 클라이언트가 보낸 데이터가 담겨있음

        // Principal: 현재 사용자의 정보를 가지고 있는 객체
        // 사용자 인증 정보(주로 이름)를 가져와서 parameter 객체에 설정

        // 현재 로그인한 사용자의 이름을 parameter 객체의 userId 필드에 설정
        parameter.setUserId(principal.getName());

        // courseService를 호출하여 수강 신청 처리를 진행
        // parameter 객체를 전달하고, 수강 신청 처리 결과를 result 변수에 저장
        ServiceResult result = courseService.req(parameter);

        if(!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            System.out.println(result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }

        // HTTP 응답을 생성하여 반환
        // ok()는 200 OK 응답 상태 코드를 나타냄
        // body(parameter)는 응답 본문에 parameter 객체를 담음
        return ResponseEntity.ok().body(parameter);

    }

}
