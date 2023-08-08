package com.example.LMS.course.model;

import lombok.Data;

@Data
public class CourseInput {

    long Id;
    long categoryId;
    String subject;
    String keyword;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;

    //삭제를 위한
    String idList;

    //ADD
    String filename;
    String urlFilename;

}
