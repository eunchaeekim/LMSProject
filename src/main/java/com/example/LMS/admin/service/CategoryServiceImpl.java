package com.example.LMS.admin.service;

import com.example.LMS.admin.dto.CategoryDto;
import com.example.LMS.admin.entity.Category;
import com.example.LMS.admin.mapper.CategoryMapper;
import com.example.LMS.admin.model.CategoryInput;
import com.example.LMS.admin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    @Override
    public List<CategoryDto> list() {
        List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());
        return CategoryDto.of(categories);
    }

    /*
    public List<CategoryDto> list() {

        List<CategoryDto> categoryDtoList = new ArrayList<>();

        List<Category> categories = categoryRepository.findAll();

        // 아래 람다식이랑 동일한 식
        if (CollectionUtils.isEmpty(categories)) {
            for (Category e : categories) {
                CategoryDto category = new CategoryDto();
                category.setId(e.getId());
                category.setCategoryName(e.getCategoryName());

                categoryDtoList.add(category);
            }
        }

        // category라는 새로운 객체 만드는 이유 : categoryDtoList는 리스트 형태로 setId 메소드가 없음

        if (CollectionUtils.isEmpty(categories)) {
            categories.forEach(e -> {
                CategoryDto category = new CategoryDto();
                category.setId(e.getId());
                category.setCategoryName(e.getCategoryName());

                categoryDtoList.add(category);
            });
        }
        return categoryDtoList;
    }
     */

    @Override
    public boolean add(String categoryName) {
        Category category = Category.builder()
                .categoryName(categoryName)
                .usingYn(true)
                .sortValue(0)
                .build();
        categoryRepository.save(category);

        return true;
    }

    @Override
    public boolean del(long id) {
        categoryRepository.deleteById(id);

        return true;
    }

    @Override
    public boolean update(CategoryInput parameter) {

        Optional<Category> optionalCategory = categoryRepository.findById(parameter.getId());
        if(optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(parameter.getCategoryName());
            category.setSortValue(parameter.getSortValue());
            category.setUsingYn(parameter.isUsingYn());
            categoryRepository.save(category);
        }
        return true;
    }

    @Override
    public List<CategoryDto> frontList(CategoryDto parameter) {
        return categoryMapper.select(parameter);
    }
}
