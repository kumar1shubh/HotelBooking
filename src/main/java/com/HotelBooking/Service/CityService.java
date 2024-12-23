package com.HotelBooking.Service;

import com.HotelBooking.Repository.CityRepository;
import com.HotelBooking.Repository.CountryRepository;
import com.HotelBooking.entity.City;
import com.HotelBooking.entity.Country;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityRepository cityRepository;

    private CountryRepository countryRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public City saveCity(City city,long countryId) {
        Country country = countryRepository.findById(countryId).get();
        city.setCountry(country);

        City saved = cityRepository.save(city);
        return saved;
    }
}
