package com.HotelBooking.Controller;

import com.HotelBooking.Payload.LoginDto;
import com.HotelBooking.Payload.TokenDto;
import com.HotelBooking.Service.UserService;
import com.HotelBooking.entity.Appuser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public  ResponseEntity<?>  signup(
            @RequestBody Appuser user
    ){
         ResponseEntity<?> savedUser= userService.saveUser(user);

         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/userLogin")
    public ResponseEntity<?> verifyLogin(
            @RequestBody LoginDto loginDto
            ){
       String  token = userService.verifyUser(loginDto);
        TokenDto tokenDto=new TokenDto();
        tokenDto.setToken(token);
        tokenDto.setType("jwt");

        return new ResponseEntity<>(tokenDto,HttpStatus.OK);
    }
    @PostMapping("/signup-propertyOwner")
    public  ResponseEntity<?>  signupOwner(
            @RequestBody Appuser user
    ){
        ResponseEntity<?> savedUser= userService.saveUserAsPropertyOwner(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity< Optional<Appuser>> getAllUsers(
            @RequestParam long Id
    ){
        Optional<Appuser> allUsers = userService.getAllUsers(Id);
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
