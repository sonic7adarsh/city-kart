package com.citykart.repos;


import com.citykart.entities.EntityProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityProductRepository extends JpaRepository<EntityProduct, Long> {

}