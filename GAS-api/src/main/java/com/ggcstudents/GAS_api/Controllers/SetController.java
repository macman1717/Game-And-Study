package com.ggcstudents.GAS_api.Controllers;

import com.ggcstudents.GAS_api.Models.CardSet;
import com.ggcstudents.GAS_api.Services.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sets")
public class SetController {

    private JwtService jwtService;
    SetController(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @PostMapping("/{setName}")
    public void createSet(@RequestHeader(name="Authorization") String token, @RequestBody CardSet set){
        if(set.getOwnerUsername().equals(jwtService.extractUsername(token))){

        }
    }
}
