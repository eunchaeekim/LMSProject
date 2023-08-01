package com.example.LMS.member.exception;

public class MemberNotEmailAuthException extends RuntimeException {
    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}

