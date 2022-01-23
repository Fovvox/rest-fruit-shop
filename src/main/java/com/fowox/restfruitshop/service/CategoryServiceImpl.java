package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.mapper.CategoryMapper;
import com.fowox.restfruitshop.api.v1.model.CategoryDTO;
import com.fowox.restfruitshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        ArrayList<CategoryDTO> result = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> result.add(categoryMapper.categoryToCategoryDTO(category)));

        return result;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findCategoryByName(name));
    }
}
