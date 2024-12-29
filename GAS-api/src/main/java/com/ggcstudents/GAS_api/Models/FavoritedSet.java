package com.ggcstudents.GAS_api.Models;

import lombok.Data;

import java.util.Date;

@Data
public class FavoritedSet {
    private String setName;
    private String setOwnerUsername;
    private Date dateLastAccessed;
}
