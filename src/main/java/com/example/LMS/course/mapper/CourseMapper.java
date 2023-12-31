package com.example.LMS.course.mapper;

import com.example.LMS.course.dto.CourseDto;
import com.example.LMS.course.model.CourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    long selectListCount(CourseParam parameter);
    List<CourseDto> selectList(CourseParam parameter);

}
