package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.dto.BlogDTOConverter;
import com.example.spring_boot_demo.dto.BlogRequestDTO;
import com.example.spring_boot_demo.dto.BlogResponseDTO;
import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.services.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {



    @Autowired
    BlogService blogService;


    @Autowired
    BlogDTOConverter blogDTOConverter;


    @GetMapping
    public List<BlogResponseDTO> getBlogPostList(@RequestParam(required = false)String username){
        return blogService.findAll(username)
                .stream()
                .map(blogPost -> blogDTOConverter.entityToResponseDTO(blogPost))
                .toList();

    }

    @GetMapping("{id}")
    public BlogResponseDTO getBlogPostById(@PathVariable("id") int id) {
        BlogPost blogPost = blogService.findBlogById(id);
        return blogDTOConverter.entityToResponseDTO((blogPost));
    }

    @PutMapping("/{id}")
    public BlogResponseDTO  updateByBlogPost(
            @PathVariable("id")int id,
            @RequestBody BlogRequestDTO changedBlogPostDTO){

        BlogPost changedBlogPost = blogDTOConverter.requestDTOToEntity(changedBlogPostDTO);

        BlogPost blogPostOut = blogService.updatBlogById(id, changedBlogPost);
        return blogDTOConverter.entityToResponseDTO(blogPostOut);


       // return blogService.updatBlogById(id, changedBlogPost);

    }






    @DeleteMapping("/{id}")
    public void deleteBlogPostById(@PathVariable("id") int id){
         blogService.DeleteById(id);
    }

    @PostMapping
    public BlogResponseDTO createNewBlogPost(@RequestBody BlogRequestDTO blogRequestDTO){
        BlogPost blogPostIn = blogDTOConverter.requestDTOToEntity(blogRequestDTO);

        BlogPost blogPostOut = blogService.CreateBlog(blogPostIn);
        return blogDTOConverter.entityToResponseDTO((blogPostOut));

    }
    


}


