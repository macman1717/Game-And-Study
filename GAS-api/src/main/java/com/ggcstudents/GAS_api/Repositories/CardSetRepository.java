package com.ggcstudents.GAS_api.Repositories;

import com.ggcstudents.GAS_api.Models.CardSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface CardSetRepository extends MongoRepository<CardSet,String> {

    @Query("{name : '?0', ownerUsername :  '?1'}")
    Optional<CardSet> findSetByNameAndOwner(String setName, String setOwner);

    @Query("{ownerUsername : '?0'}")
    ArrayList<CardSet> findAllByUsername(String username);
}
