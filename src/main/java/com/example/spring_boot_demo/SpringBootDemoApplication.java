package com.example.spring_boot_demo;

import com.example.spring_boot_demo.entities.AppUser;
import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.repositories.AppUserRepository;
import com.example.spring_boot_demo.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootDemoApplication implements CommandLineRunner {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        AppUser appUser = new AppUser("Gunnar");
        AppUser appUser2 = new AppUser("Alince");

        appUserRepository.saveAll(List.of(appUser, appUser2));

        BlogPost blogPost = new BlogPost("1","vackert väder", appUser);
        BlogPost blogPost2 = new BlogPost("Post 2", "Fiskade fisk idag", appUser);
        BlogPost blogPost3 = new BlogPost("Hej från Alice", "ville bara säga hej till världen jag också", appUser);
        blogPostRepository.saveAll(List.of(blogPost, blogPost2, blogPost3));

    }
}
