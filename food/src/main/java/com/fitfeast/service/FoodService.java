package com.fitfeast.service;

import com.fitfeast.model.Category;

import com.fitfeast.model.Food;
import com.fitfeast.model.Restaurant;
import com.fitfeast.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
    public void deleteFood(Long foodId) throws  Exception;
    public List<Food> getRestaurantFood(Long restaurantId,boolean  isVeg,boolean isNonVeg,boolean isSeasonal,String foodCategory);
    public List<Food> searchFood(String Keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailableStatus(Long foodId) throws Exception;
}
