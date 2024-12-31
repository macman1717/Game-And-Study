package com.ggcstudents.GAS_api.Controllers;

import com.ggcstudents.GAS_api.Models.AuthRequest;
import com.ggcstudents.GAS_api.Models.GasUser;
import com.ggcstudents.GAS_api.Repositories.GasUserRepository;
import com.ggcstudents.GAS_api.Services.GasUserDetails;
import com.ggcstudents.GAS_api.Services.GasUserService;
import com.ggcstudents.GAS_api.Services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final GasUserService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final GasUserRepository userRepository;
    UserController(GasUserService service, JwtService jwtService, AuthenticationManager authenticationManager, GasUserRepository userRepository){
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody GasUser gasUser){
        String response = service.addUser(gasUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        }else{
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/userEmail")
    public String getEmail(@RequestHeader (name="Authorization") String token){
        token = token.split(" ")[1].trim();
        Optional<GasUser> gasUser = userRepository.findUserByUsername(jwtService.extractUsername(token));
        if(gasUser.isPresent()){
            return gasUser.get().getEmail();
        }
        return "Invalid Request";
    }

    // testtt
}
