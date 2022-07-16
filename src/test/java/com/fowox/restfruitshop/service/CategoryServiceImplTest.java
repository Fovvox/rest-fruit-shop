package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.mapper.CategoryMapper;
import com.fowox.restfruitshop.api.v1.model.CategoryDTO;
import com.fowox.restfruitshop.domain.Category;
import com.fowox.restfruitshop.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {
    public static final String NAME = "name";
    public static final long ID = 2L;

    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;

    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void getAllCategories() {
        List<Category> categoryList = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();

        assertEquals(3, categoryDTOList.size());
    }

    @Test
    void getCategoryByName() {
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findCategoryByName(anyString())).thenReturn(Optional.of(category));

        CategoryDTO  categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(NAME, categoryDTO.getName());
        assertEquals(ID, categoryDTO.getId());
    }
}