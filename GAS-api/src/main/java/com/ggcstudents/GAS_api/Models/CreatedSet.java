package com.ggcstudents.GAS_api.Models;

import lombok.Data;

import java.util.Date;

@Data
public class CreatedSet {
    private String setName;
    private String dateLastAccessed;
    private int numOfTerms;
}
