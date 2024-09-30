package com.onboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onboard.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}