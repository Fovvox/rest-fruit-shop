package com.fowox.restfruitshop.api.v1.mapper;

import com.fowox.restfruitshop.api.v1.model.CategoryDTO;
import com.fowox.restfruitshop.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setName("name");
        category.setId(1L);

        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);

        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getId(), categoryDTO.getId());
    }
}