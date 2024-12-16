package com.ggcstudents.GAS_api.Repositories;

import com.ggcstudents.GAS_api.Models.GasUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface GasUserRepository extends MongoRepository<GasUser, String> {
    @Query("{username: '?0'}")
    Optional<GasUser> findUserByUsername(String username);

}
