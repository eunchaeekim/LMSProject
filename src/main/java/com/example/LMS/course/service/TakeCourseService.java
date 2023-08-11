package com.example.LMS.course.service;

import com.example.LMS.course.dto.TakeCourseDto;
import com.example.LMS.course.model.ServiceResult;
import com.example.LMS.course.model.TakeCourseParam;

import java.util.List;

public interface TakeCourseService {

    /**
     * 수강 목록
     */
    List<TakeCourseDto> list (TakeCourseParam parameter);

    /**
     * 수강 내용 상태 반영
     */
    ServiceResult updateStatus(long id, String status);


}
