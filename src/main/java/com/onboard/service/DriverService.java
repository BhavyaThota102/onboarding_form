package com.onboard.service;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.DTO.DriverDto;
import com.onboard.EncryptionDecryption.Encryp;
import com.onboard.entities.Driver;
import com.onboard.repository.DriverRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DriverService {
    @Autowired
    private DriverRepository driverRepository; // Repository for database operations
    @Autowired
    private SecretKey secretkey;
    
    
    
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll(); // Get all drivers
    }

    public DriverDto getDriverById(Long id) throws Exception {
    	
    	

        Optional<Driver> arr=driverRepository.findById(id);
        DriverDto driver=new DriverDto();
        driver.setName(Encryp.decrypt(arr.get().getFirstName(), secretkey));
        driver.setPhonenumber(Encryp.decrypt(arr.get().getPhoneNumber(), secretkey));
        System.out.println(driver.getName()+"     "+driver.getPhonenumber());
        return driver;
       
    }

    public String createDriver(Driver driver) throws Exception {
       
        if (driver.getPartnerName() == null || driver.getPartnerName().trim().isEmpty()) {
            return "Partner name should not be empty.";
        }

            // Check if the first name is empty
            if (driver.getFirstName() == null || driver.getFirstName().trim().isEmpty()) {
                return "First name should not be empty";
            }
            
            // Check if the last name is empty
            if (driver.getLastName() == null || driver.getLastName().trim().isEmpty()) {
                return "Last name should not be empty";
            }
            
            // Check if the phone number is valid
            String phonePattern = "^(\\+91[0-9]{10}|0[0-9]{10}|[0-9]{10})$";
            if (driver.getPhoneNumber() == null || !driver.getPhoneNumber().matches(phonePattern)) {
                return "Invalid mobile number";
            }

            // Validate the referral code (e.g., check if it matches a specific format)
            String referralCodePattern = "^[A-Za-z0-9]{5,10}$"; // Example pattern: 5 to 10 alphanumeric characters
            if (driver.getReferralCode() == null || !driver.getReferralCode().matches(referralCodePattern)) {
                return "Invalid referral code. It must be 5 to 10 alphanumeric characters.";
            }
            driver.setFirstName(Encryp.encrypt(driver.getFirstName(), secretkey));
            driver.setLastName(Encryp.encrypt(driver.getLastName(), secretkey));
            driver.setMiddleName(Encryp.encrypt(driver.getMiddleName(), secretkey));
            driver.setPartnerName(Encryp.encrypt(driver.getPartnerName(), secretkey));
            driver.setPhoneNumber(Encryp.encrypt(driver.getPhoneNumber(), secretkey));
            driver.setReferralCode(Encryp.encrypt(driver.getReferralCode(), secretkey));
            driverRepository.save(driver);
            return "Successfully data is stored"; // Return empty string if all validations pass
        }// Return success message
    

   /* public void deleteDriver(Long id) {
        driverRepository.deleteById(id); // Delete a driver by ID
    }*/

}
