package com.example.spring_boot_demo.dto;

import com.example.spring_boot_demo.entities.AppUser;
import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogDTOConverter {
    @Autowired
    AppUserRepository appUserRepository;

    public BlogResponseDTO entityToResponseDTO(BlogPost blogPost){

        return  new BlogResponseDTO(blogPost.getId(), blogPost.getTitle(), blogPost.getMessage(), blogPost.getAppUser().getId());

    }

    public BlogPost requestDTOToEntity(BlogRequestDTO blogRequestDTO){

        AppUser appUser = appUserRepository.findById(blogRequestDTO.getAppUserId()).orElseThrow();

        return new BlogPost(blogRequestDTO.getTitle(), blogRequestDTO.getMessage(), appUser);
    }
}