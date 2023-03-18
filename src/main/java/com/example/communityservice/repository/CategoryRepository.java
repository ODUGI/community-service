package com.example.communityservice.repository;

import com.example.communityservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCommunity_Id(Long id);


}
