package com.example.server.repository;

import com.example.server.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJPARepository extends JpaRepository<Category, Short> {
}
