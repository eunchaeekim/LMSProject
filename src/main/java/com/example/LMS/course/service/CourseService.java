package com.example.LMS.course.service;

import com.example.LMS.course.dto.CourseDto;
import com.example.LMS.course.model.CourseInput;
import com.example.LMS.course.model.CourseParam;
import com.example.LMS.course.model.ServiceResult;
import com.example.LMS.course.model.TakeCourseInput;

import java.util.List;

public interface CourseService {

    /**
     * 강좌 등록
     */
    boolean add(CourseInput parameter);

    /**
     * 강좌 목록
     */
    List<CourseDto> list(CourseParam parameter);

    /**
     * 강좌 상세정보
     */
    CourseDto getById(long id);

    /**
     * 강좌 정보 수정
     */
    boolean set(CourseInput parameter);

    /**
     * 강좌 내용 삭제
     */
    boolean del(String idList);

    /**
     * 프론트 강좌 목록
     */
    List<CourseDto> frontList(CourseParam parameter);

    /**
     * 프론트 강좌 상세 정보
     */
    CourseDto frontDetail(long id);

    /**
     * 수강 신청
     */
    ServiceResult req(TakeCourseInput parameter);
}
