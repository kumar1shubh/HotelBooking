package com.HotelBooking.Service;

import com.HotelBooking.Payload.LoginDto;
import com.HotelBooking.Payload.SignupDto;
import com.HotelBooking.Payload.TokenDto;
import com.HotelBooking.Repository.AppuserRepository;
import com.HotelBooking.entity.Appuser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private AppuserRepository appuserRepository;

    private JWTService jwtService;

    public UserService(AppuserRepository appuserRepository, JWTService jwtService) {
        this.appuserRepository = appuserRepository;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> saveUser(Appuser user) {
        Optional<Appuser> opEmail = appuserRepository.findByEmail(user.getEmail());

        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email is already in use try with another email", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {


            Optional<Appuser> opUsername = appuserRepository.findByUsername(user.getUsername());

            if (opUsername.isPresent()) {
                return new ResponseEntity<>("Username is already in use try with another username", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");

        Appuser save = appuserRepository.save(user);

        SignupDto dto = new SignupDto();
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getMobile());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public String verifyUser(LoginDto loginDto) {
        Optional<Appuser> opUsername = appuserRepository.findByUsername(loginDto.getUsername());
        if (opUsername.isPresent()) {
            Appuser appuser = opUsername.get();

            if (BCrypt.checkpw(loginDto.getPassword(), appuser.getPassword())) {
//               generate token

                String token = jwtService.generateToken(appuser.getUsername());
                if (token != null) {
                   return token;
                }


            } else {
                return "Username  password not found";
            }
            return null;
        }
        return null;
    }

    public ResponseEntity<?> saveUserAsPropertyOwner(Appuser user) {
        Optional<Appuser> opEmail = appuserRepository.findByEmail(user.getEmail());

        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email is already in use try with another email", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {


            Optional<Appuser> opUsername = appuserRepository.findByUsername(user.getUsername());

            if (opUsername.isPresent()) {
                return new ResponseEntity<>("Username is already in use try with another username", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(hashpw);
        user.setRole("ROLE_OWNER");

        Appuser save = appuserRepository.save(user);

        SignupDto dto = new SignupDto();
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getMobile());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public Optional<Appuser> getAllUsers(long id) {
        Optional<Appuser> appuser = appuserRepository.findById(id);
        return appuser;
    }
}
