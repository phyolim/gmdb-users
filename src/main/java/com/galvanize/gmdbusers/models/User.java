package com.galvanize.gmdbusers.models;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;


@Entity
@Data
@Repository

public class User {
    /**
     * User Entity
     * email and password are required to create a user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(){
    }
}
