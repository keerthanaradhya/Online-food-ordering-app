package com.fitfeast.repository;

import com.fitfeast.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :searchQuery, '%')) " +
            "OR lower(r.cuisineType) LIKE lower(concat('%', :searchQuery, '%'))")
    List<Restaurant> findBySearchQuery(@Param("searchQuery") String searchQuery);
    
    Restaurant findByOwnerId(Long userId);
}
