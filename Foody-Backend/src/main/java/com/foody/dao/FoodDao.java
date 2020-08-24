package com.foody.dao;

import com.foody.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodDao extends JpaRepository<Food,Long> {

}
