package com.HotelBooking.Repository;

import com.HotelBooking.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppuserRepository extends JpaRepository<Appuser, Long> {
  Optional<Appuser> findByEmail(String email);
  Optional<Appuser> findByUsername(String username);
}