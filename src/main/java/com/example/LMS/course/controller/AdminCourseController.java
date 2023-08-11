package com.example.LMS.course.controller;

import com.example.LMS.admin.service.CategoryService;
import com.example.LMS.course.dto.CourseDto;
import com.example.LMS.course.model.CourseInput;
import com.example.LMS.course.model.CourseParam;
import com.example.LMS.course.service.CourseService;
import com.example.LMS.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/admin/course/list.do")
    public String list(Model model, CourseParam parameter) {

        parameter.init();
        List<CourseDto> courseList = courseService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/ecourse/list";
    }

    @GetMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String add(Model model, HttpServletRequest request, CourseInput parameter) {
        // Model 객체: 뷰로 데이터를 전달하기 위해 사용되는 스프링의 모델 객체
        // HttpServletRequest 객체: 요청과 관련된 정보를 얻기 위해 사용되는 서블릿의 HttpServletRequest 객체
        // CourseInput 객체: 강좌 정보를 담고 있는 사용자 입력 데이터 객체

        model.addAttribute("category", categoryService.list());

        // 요청이 편집 모드인지 확인하는 변수
        boolean editMode = request.getRequestURI().contains("/edit.do");

        // 새로운 CourseDto 객체 생성
        CourseDto detail = new CourseDto();

        // 편집 모드일 경우
        if (editMode) {
            // CourseInput 객체에서 강좌 ID를 얻어옴
            long id = parameter.getId();

            // ID에 해당하는 강좌 정보를 데이터베이스에서 가져옴
            CourseDto existCourse = courseService.getById(id);

            // 가져온 강좌 정보가 없을 경우 에러 처리
            if (existCourse == null) {
                // 에러 메시지를 Model에 추가하여 뷰로 전달하고, 에러 페이지로 이동
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }

            // 가져온 강좌 정보를 detail 변수에 저장
            detail = existCourse;
        }

        // 뷰에 편집 모드 정보와 강좌 정보를 전달하기 위해 Model에 속성 추가
        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);


        // "admin/course/add" 뷰로 이동
        return "admin/course/add";
    }


    @PostMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
            , CourseInput parameter) {


        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = courseService.set(parameter);

        } else {
            boolean result = courseService.add(parameter);
        }

        return "redirect:/admin/course/list.do";
    }

    @PostMapping("/admin/course/delete.do")
    public String del(Model model, HttpServletRequest request, CourseInput parameter) {
        boolean result = courseService.del(parameter.getIdList());

        return "redirect:/admin/course/list.do";
    }
}
