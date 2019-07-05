package com.ipa.demo.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class User_Images {

    @Id
    private String id;



    @ManyToOne
    private String username;
}
