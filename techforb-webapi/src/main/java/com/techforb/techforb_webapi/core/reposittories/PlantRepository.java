package com.techforb.techforb_webapi.core.reposittories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforb.techforb_webapi.core.models.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {

}
