package com.HotelBooking.Controller;

import com.HotelBooking.Payload.CityDto;
import com.HotelBooking.Service.CityService;
import com.HotelBooking.entity.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/save")

    public ResponseEntity<CityDto> saveCity(
            @RequestBody City city,
            @RequestParam long countryId
    ) {
        City city1 = cityService.saveCity(city,countryId );
        CityDto cityDto= new CityDto();
        cityDto.setName(city1.getName());
        return new ResponseEntity<>(cityDto, HttpStatus.CREATED);
    }
}
