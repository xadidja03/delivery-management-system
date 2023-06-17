package com.example.demo.repository;

import com.example.demo.entity.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {

}
