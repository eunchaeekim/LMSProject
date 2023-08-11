package com.example.LMS.course.model;

import com.example.LMS.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeCourseParam extends CommonParam {

    long id;
    String status;

    String userId;

    long searchCourseId;

}