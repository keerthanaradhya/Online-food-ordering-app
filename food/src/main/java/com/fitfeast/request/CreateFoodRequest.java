package com.fitfeast.request;

import com.fitfeast.model.Category;
import com.fitfeast.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean veg;
    private boolean seasonal;
    private List<IngredientsItem> ingredients;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public boolean isVeg() {
		return veg;
	}
	public void setVeg(boolean veg) {
		this.veg = veg;
	}
	public boolean isSeasonal() {
		return seasonal;
	}
	public void setSeasonal(boolean seasonal) {
		this.seasonal = seasonal;
	}
	public List<IngredientsItem> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<IngredientsItem> ingredients) {
		this.ingredients = ingredients;
	}
    
    
}
