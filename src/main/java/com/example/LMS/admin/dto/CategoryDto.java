package com.example.LMS.admin.dto;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    Long id;
    String categoryName;
    int sortValue;
    boolean usingYn;
