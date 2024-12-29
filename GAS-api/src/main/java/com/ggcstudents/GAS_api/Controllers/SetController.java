package com.ggcstudents.GAS_api.Controllers;

import com.ggcstudents.GAS_api.Models.CardSet;
import com.ggcstudents.GAS_api.Models.CreatedSet;
import com.ggcstudents.GAS_api.Models.Library;
import com.ggcstudents.GAS_api.Repositories.CardSetRepository;
import com.ggcstudents.GAS_api.Repositories.LibraryRepository;
import com.ggcstudents.GAS_api.Services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/sets")
public class SetController {

    private final JwtService jwtService;
    private final CardSetRepository setRepo;
    private final LibraryRepository libraryRepository;
    SetController(JwtService jwtService, CardSetRepository setRepo, LibraryRepository libraryRepository){
        this.jwtService = jwtService;
        this.setRepo = setRepo;
        this.libraryRepository = libraryRepository;
    }

    @PostMapping("create-set/{setName}")
    public ResponseEntity<String> createSet(@RequestHeader(name="Authorization") String token, @RequestBody CardSet set){
        if(set.getOwnerUsername().equals(jwtService.extractUsername(token))){
            Optional<CardSet> optionalSet = setRepo.findSetByNameAndOwner(set.getName(), set.getOwnerUsername());
            if(optionalSet.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Set with name " + set.getName() + " already exists");
            }else{
                setRepo.insert(set);
                CreatedSet createdSet = new CreatedSet();
                createdSet.setSetName(set.getName());
                createdSet.setNumOfTerms(set.getCards().size());

                Calendar cal = Calendar.getInstance();
                Date date=cal.getTime();
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String formattedDate=dateFormat.format(date);
                createdSet.setDateLastAccessed(formattedDate);

                Optional<Library> optLib = libraryRepository.findLibraryByUser(set.getOwnerUsername());
                if(optLib.isPresent()) {
                    Library lib = optLib.get();
                    lib.getCreatedSets().add(createdSet);
                    libraryRepository.save(lib);
                }

            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
