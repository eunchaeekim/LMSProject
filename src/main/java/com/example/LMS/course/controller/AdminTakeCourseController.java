package com.example.LMS.course.controller;

import com.example.LMS.course.dto.CourseDto;
import com.example.LMS.course.dto.TakeCourseDto;
import com.example.LMS.course.model.ServiceResult;
import com.example.LMS.course.model.TakeCourseParam;
import com.example.LMS.course.service.CourseService;
import com.example.LMS.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends BaseController {

    private final TakeCourseService takeCourseService;

    @GetMapping("/admin/takecourse/list.do")
    public String list(Model model, TakeCourseParam parameter) {

        parameter.init();
        List<TakeCourseDto> courseList = takeCourseService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);


        return "admin/takecourse/list";
    }

    @PostMapping("/admin/takecourse/status.do")
    public String status(Model model, TakeCourseParam parameter) {

        ServiceResult result = takeCourseService.updateStatus(parameter.getId(), parameter.getStatus());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }
        return "redirect:/admin/takecourse/list.do";

    }
}