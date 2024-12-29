package com.ggcstudents.GAS_api.Repositories;

import com.ggcstudents.GAS_api.Models.CardSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CardSetRepository extends MongoRepository<CardSet,String> {

    @Query("{name : '?0', ownerUsername :  '?1'}")
    Optional<CardSet> findSetByNameAndOwner(String setName, String setOwner);
}
