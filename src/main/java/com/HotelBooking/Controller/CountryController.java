package com.HotelBooking.Controller;

import com.HotelBooking.Repository.CountryRepository;
import com.HotelBooking.entity.Country;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    @PostMapping("/add")
    public String AddCountry(){
        return "added";
    }

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    @DeleteMapping("/remove")
    public void deleteCountry(@RequestParam Long countryId) {
        // Check if the country exists
        Optional<Country> countryOptional = countryRepository.findById(countryId);
        if (countryOptional.isPresent()) {
            // Delete the country; associated cities will be removed automatically
            countryRepository.delete(countryOptional.get());
        }
    }
}
