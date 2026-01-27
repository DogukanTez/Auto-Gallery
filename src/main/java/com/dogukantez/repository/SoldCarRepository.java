package com.dogukantez.repository;

import com.dogukantez.entities.SoldCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldCarRepository extends JpaRepository<SoldCar,Long> {
}
