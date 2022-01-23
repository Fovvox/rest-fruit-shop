package com.fowox.restfruitshop.api.v1.mapper;


import com.fowox.restfruitshop.api.v1.model.CategoryDTO;
import com.fowox.restfruitshop.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
