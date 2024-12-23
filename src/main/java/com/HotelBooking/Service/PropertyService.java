package com.HotelBooking.Service;

import com.HotelBooking.Repository.CityRepository;
import com.HotelBooking.Repository.CountryRepository;
import com.HotelBooking.Repository.PropertyRepository;
import com.HotelBooking.entity.City;
import com.HotelBooking.entity.Country;
import com.HotelBooking.entity.Property;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public PropertyService(PropertyRepository propertyRepository, CountryRepository countryRepository, CityRepository cityRepository) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    public Property saveProperty(Property property, long countryId, long cityId) {
        Country country = countryRepository.findById(countryId).get();
        property.setCountry(country);
        City city = cityRepository.findById(cityId).get();
        property.setCity(city);

        Property saved = propertyRepository.save(property);
        return saved;
    }
}
