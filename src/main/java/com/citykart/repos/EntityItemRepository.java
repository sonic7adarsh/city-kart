package com.citykart.repos;

import com.citykart.entities.EntityItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityItemRepository extends JpaRepository<EntityItem, Long> {

}