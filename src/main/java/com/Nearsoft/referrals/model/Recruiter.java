package com.Nearsoft.referrals.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recruiter")
public class Recruiter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Recrutier[id=%d, naame='%s']", id, name);
    }

}