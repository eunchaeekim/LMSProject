package com.example.LMS.course.mapper;

import com.example.LMS.course.dto.TakeCourseDto;
import com.example.LMS.course.model.TakeCourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeCourseMapper {

    long selectListCount(TakeCourseParam parameter);

    List<TakeCourseDto> selectList(TakeCourseParam parameter);


}
