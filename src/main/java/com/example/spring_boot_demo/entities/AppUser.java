package com.example.spring_boot_demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;




   @Column(nullable = false, unique = true)
   private String username;

    //Added
    //Lägger till email
    @Column(nullable = false)
    private String email;

   @OneToMany(mappedBy = "appUser")
   @JsonIgnore
   private Set<BlogPost> blogPosts;





    public AppUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public AppUser(){


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Set<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBlogPosts(Set<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }
}
