package com.ggcstudents.GAS_api.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("Users")
public class GasUser {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String roles;
    private Date date_created;
}
