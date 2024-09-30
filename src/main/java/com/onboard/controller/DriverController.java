package com.onboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onboard.DTO.DriverDto;
import com.onboard.entities.Driver;
import com.onboard.service.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
	@Autowired
    private DriverService driverService;

    @GetMapping("/getAllDrivers")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("getDriverByIdDetails/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable Long id) {
    	System.out.println(id);
        try {
            DriverDto driverDto = driverService.getDriverById(id);
            return ResponseEntity.ok(driverDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return a 404 status if not found
        }
    }

    @PostMapping("/createDriverDetails")
    public String createDriver(@RequestBody Driver driver) throws Exception{
        return driverService.createDriver(driver);
    }

   
    /*@DeleteMapping("deleteDriver/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }*/
}
