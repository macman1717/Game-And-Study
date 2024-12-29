package com.ggcstudents.GAS_api.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Data
@Document("Sets")
public class CardSet {
    @Id
    private String id;
    private String name;
    private String ownerUsername;
    private boolean visibility;
    private Date dateCreated;
    private ArrayList<Card> cards;
}
