package com.fowox.restfruitshop.repository;

import com.fowox.restfruitshop.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
