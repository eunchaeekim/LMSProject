package com.example.LMS.admin.controller;

import com.example.LMS.admin.model.MemberParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {

    @GetMapping("/admin/category/list.do")
    public String list(Model model, MemberParam parameter) {


        return "admin/category/list";
    }


    @PostMapping("/admin/category/add.do")
    public String add(Model model, MemberParam parameter) {


        return null;
    }

}
