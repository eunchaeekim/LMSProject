package com.example.LMS.admin.mapper;

import com.example.LMS.admin.dto.MemberDto;
import com.example.LMS.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    // 아래 메소드에 대해 xml에 모두 구현되어야함
    // Ex) <select id="selectListCount"
    //            parameterType="com.example.LMS.admin.model.MemberParam"
    //            resultType="Long">

    long selectListCount(MemberParam parameter);
    List<MemberDto> selectList(MemberParam parameter);

}
