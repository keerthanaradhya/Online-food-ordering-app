package com.fitfeast.service;

import com.fitfeast.model.Category;
import com.fitfeast.model.Food;
import com.fitfeast.model.Restaurant;
import com.fitfeast.repository.FoodRepository;
import com.fitfeast.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVeg(req.isVeg());
        Food saveFood = foodRepository.save(food);
        restaurant.getFoods().add(saveFood);

        return saveFood;

    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
    Food food = findFoodById(foodId);
    food.setRestaurant(null);
    foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVeg, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if (isVeg){
            foods = filterByVeg(foods,isVeg);
        }
        if (isNonVeg){
            foods = filterByNonVeg(foods,isNonVeg);

        }
        if (isSeasonal){
            foods = filterBySeasonal(foods,isSeasonal);
        }
        if (foodCategory!=null && !foodCategory.equals("")){
            foods = filterByFoodCategory(foods,foodCategory);

        }
        return foods;
    }

    private List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if (food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return  foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
        return  foods.stream().filter(food -> food.isVeg()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVeg(List<Food> foods, boolean isVeg) {
        return  foods.stream().filter(food -> food.isVeg()==isVeg).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String Keyword) {
        return foodRepository.searchFood(Keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isEmpty()){
            throw new Exception("Food does not exist...");

        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailableStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());

        return foodRepository.save(food);
    }
}
