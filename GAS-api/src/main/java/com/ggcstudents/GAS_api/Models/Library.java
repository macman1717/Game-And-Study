package com.ggcstudents.GAS_api.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@Document("Libraries")
public class Library {
    @Id
    private String id;
    private String ownerUsername;
    private ArrayList<CreatedSet> createdSets;
    private ArrayList<FavoritedSet> favoritedSets;
}
