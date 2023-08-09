package com.example.LMS.course.repository;

import com.example.LMS.course.entity.TakeCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TakeCourseRepository extends JpaRepository<TakeCourse, Long> {
    // courseId, userId, 그리고 여러 개의 status 값들을 기반으로 데이터베이스에서 조건을 만족하는 엔티티(레코드)의 개수를 계산
    long countByCourseIdAndUserIdAndStatusIn(long courseId, String userId, Collection<String> statusList);

}