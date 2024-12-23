package com.HotelBooking.Config;

import com.HotelBooking.Repository.AppuserRepository;
import com.HotelBooking.Service.JWTService;
import com.HotelBooking.entity.Appuser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private AppuserRepository appuserRepository;


    public JwtFilter(JWTService jwtService, AppuserRepository appuserRepository) {
        this.jwtService = jwtService;
        this.appuserRepository = appuserRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String token=  request.getHeader("Authorization");
     if (token != null && token.startsWith("Bearer ")){
         String tokenVal =token.substring(8,token.length()-1);
         String username = jwtService.getUsername(tokenVal);
        Optional<Appuser> opuser = appuserRepository.findByUsername(username);
        if (opuser.isPresent()){ //if this is present then we need to tell spring security to go further with other procces
            Appuser appuser = opuser.get();
            // need this user to acces certin details.  creadentil is null because it requrir password
            UsernamePasswordAuthenticationToken authenticationToken=
                             new UsernamePasswordAuthenticationToken(appuser,null, Collections.singleton(new SimpleGrantedAuthority(appuser.getRole())));
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        }
     }
        filterChain.doFilter(request,response);
    }

}
