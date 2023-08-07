package com.example.LMS.member.service;


import com.example.LMS.admin.dto.MemberDto;
import com.example.LMS.admin.model.MemberParam;
import com.example.LMS.member.entity.Member;
import com.example.LMS.member.model.MemberInput;
import com.example.LMS.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 계정을 활성화 함.
     */
   boolean emailAuth(String uuid);

   /**
    * 입력한 이메일로 비밀번호 초기화 정보를 전송
    */
   boolean sendResetPassword(ResetPasswordInput parameter);

    /**
     * 입력받은 uuid에 대해서 password로 초기화 함
     */
    boolean resetPassword(String uuid, String password);

    /**
     * 입력받은 uuid값이 유효한지 확인
     */
    boolean checkResetPassword(String uuid);

    /**
     * 회원 목록 리턴(관리자에서만 가능)
     */
    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상제 정보
     */
    MemberDto detail(String userId);

    /**
     * 회원 상태 변경
     */
    boolean updateStatus(String userId, String userStatus);

    /**
     * 회원 비밀번호 초기화
     */
    boolean updatePassword(String userId, String password);
}