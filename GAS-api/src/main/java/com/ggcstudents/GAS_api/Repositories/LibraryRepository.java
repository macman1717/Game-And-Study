package com.ggcstudents.GAS_api.Repositories;


import com.ggcstudents.GAS_api.Models.Library;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface LibraryRepository extends MongoRepository<Library, String> {
    @Query("{ownerUsername : '?0'}")
    Optional<Library> findLibraryByUser(String username);
}
