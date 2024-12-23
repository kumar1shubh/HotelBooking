package com.HotelBooking.Payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {

    private String name;

    private String username;

    private String email;

    private String mobile;
}
