package com.example.spring_boot_demo;

import com.example.spring_boot_demo.entities.AppUser;
import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.repositories.AppUserRepository;
import com.example.spring_boot_demo.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        appUserRepository.save(appUser);

        BlogPost blogPost = new BlogPost("1","vackert väder", appUser);
                blogPostRepository.save(blogPost);

    }
}
