package com.HotelBooking.Repository;

import com.HotelBooking.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {


}