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
import java.util.ArrayList;
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

    @GetMapping("/sets")
    public ArrayList<CardSet> getSets(@RequestHeader(name="Authorization") String token){
        token = token.split(" ")[1].trim();
        String username = jwtService.extractUsername(token);
        return setRepo.findAllByUsername(username);
    }
    @GetMapping("/sets/{setName}")
    public CardSet getSet(@RequestHeader(name="Authorization") String token, @PathVariable String setName){
        token = token.split(" ")[1].trim();
        String username = jwtService.extractUsername(token);
        Optional<CardSet> optSet = setRepo.findSetByNameAndOwner(setName, username);
        return optSet.orElse(null);
    }
    @PostMapping("/create-set")
    public ResponseEntity<String> createSet(@RequestHeader(name="Authorization") String token, @RequestBody CardSet set){
        token = token.split(" ")[1].trim();
        if(set.getOwnerUsername().equals(jwtService.extractUsername(token))){
            Optional<CardSet> optionalSet = setRepo.findSetByNameAndOwner(set.getName(), set.getOwnerUsername());
            if(optionalSet.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Set with name " + set.getName() + " already exists");
            }else{
                
                CreatedSet createdSet = new CreatedSet();
                createdSet.setSetName(set.getName());
                createdSet.setNumOfTerms(set.getCards().size());

                Calendar cal = Calendar.getInstance();
                Date date=cal.getTime();
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDate=dateFormat.format(date);
                createdSet.setDateLastAccessed(formattedDate);
                set.setDateCreated(formattedDate);
                setRepo.insert(set);

                Optional<Library> optLib = libraryRepository.findLibraryByUser(set.getOwnerUsername());
                if(optLib.isPresent()) {
                    Library lib = optLib.get();
                    lib.getCreatedSets().add(createdSet);
                    libraryRepository.save(lib);
                }
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/delete-set/{setName}")
    public ResponseEntity<String> deleteSet(@RequestHeader(name="Authorization") String token, @PathVariable String setName) {
        System.out.println("Called");
        token = token.split(" ")[1].trim();
        String username = jwtService.extractUsername(token);
        Optional<CardSet> optSet = setRepo.findSetByNameAndOwner(setName, username);
        if(optSet.isPresent()){
           CardSet set =  optSet.get();
           System.out.println(username);
           System.out.println(set.getOwnerUsername());
           if(set.getOwnerUsername().equals(username)){
               setRepo.delete(set);
               return ResponseEntity.status(HttpStatus.OK).build();
           }else{
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
           }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update-set")
    public ResponseEntity<String> updateSet(@RequestHeader(name="Authorization") String token, @RequestBody CardSet set){
        token = token.split(" ")[1].trim();
        String username = jwtService.extractUsername(token);
        if(username.equals(set.getOwnerUsername())){
            setRepo.save(set);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
