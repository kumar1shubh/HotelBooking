package com.HotelBooking.Controller;

import com.HotelBooking.Service.PropertyService;
import com.HotelBooking.entity.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    @PostMapping("/addProperty")
    public ResponseEntity<Property> saveProperty(
            @RequestBody Property property,
            @RequestParam long countryId,
            @RequestParam long cityId

    ){
        Property property1 = propertyService.saveProperty(property, countryId, cityId);
        return new ResponseEntity<>(property1, HttpStatus.CREATED);
    }
}
