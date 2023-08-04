package com.example.LMS.admin.controller;

import com.example.LMS.admin.dto.MemberDto;
import com.example.LMS.admin.model.MemberParam;
import com.example.LMS.util.PageUtil;
import com.example.LMS.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {
    private final MemberService memberService;

    // 회원 목록 조회 페이지로 이동하는 메소드
    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter) {
        parameter.init();

        List<MemberDto> members = memberService.list(parameter);

        long totalCount = 0;
        if (members != null && members.size() > 0 ) {
            totalCount = members.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();

        // 페이징 처리를 위한 PageUtil 객체 생성 및 초기화
        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        // 생성된 페이징 HTML 코드를 "pager"라는 이름으로 모델에 추가하여 뷰로 전달
        model.addAttribute("pager", pageUtil.pager());
        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);



        // 회원 목록을 출력하는 뷰 페이지로 이동
        return "admin/member/list";
    }
}
