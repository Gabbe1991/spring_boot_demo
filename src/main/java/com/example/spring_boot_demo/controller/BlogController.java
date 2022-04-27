package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.dto.BlogDTOConverter;
import com.example.spring_boot_demo.dto.BlogRequestDTO;
import com.example.spring_boot_demo.dto.BlogResponseDTO;
import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.services.BlogPostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    //@Autowired
    //BlogPostService blogService;


    BlogPostService blogPostService;
    BlogDTOConverter blogDTOConverter;

    public BlogController(BlogPostService blogPostService, BlogDTOConverter blogDTOConverter){
        this.blogPostService = blogPostService;
        this.blogDTOConverter = blogDTOConverter;
    }

    @GetMapping
    public String getBlogPostList(@RequestParam(required = false)String username, Model model){
        List<BlogPost> blogPostList = blogPostService.findAll();
        model.addAttribute("blogPostList", blogPostList);
        return "blog";
    }

    @GetMapping("/addBlog")
    public String addBlog(){
        return "addBlogForm";
    }

    @GetMapping("/{id}/editBlog")
    public String editBlogpost(@PathVariable int id, Model model){
        BlogPost blogPost = blogPostService.findBlogById(id);
        model.addAttribute("blogpost", blogPost);
        return "editBlogForm";
    }

    @GetMapping("/{id}")
    public BlogResponseDTO getBlogPostById(@PathVariable("id") int id) {
        BlogPost blogPost = blogPostService.findBlogById(id);
        return blogDTOConverter.entityToResponseDTO((blogPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPostById(@PathVariable("id") int id){
        blogPostService.deleteById(id);
        return ResponseEntity.status(303).header("Location", "/blog").build();
    }

    /*
    @PostMapping
    public BlogResponseDTO createBlogPost(@RequestBody BlogRequestDTO blogRequestDTO){
        BlogPost blogPost = blogDTOConverter.BlogRequestDTOToEntity(blogRequestDTO);
        blogPost = blogPostService.save(blogPost);
        return blogDTOConverter.entityToResponseDTO(blogPost);
    }*/

    @PostMapping
    public String createBlogPost(@ModelAttribute BlogPost blogPost){
        blogPostService.save(blogPost);
        return "redirect:/blog";
    }

    @PostMapping("/{id}")
    public String editBlogPost(@PathVariable("id") int id, @ModelAttribute BlogPost blogPost){
        blogPostService.updateBlogById(id, blogPost);
        return "redirect:/blog";
    }




    @PutMapping("/{id}")
    public BlogResponseDTO  updateBlogPostById(
            @PathVariable("id")int id,
            @RequestBody BlogRequestDTO changedBlogPostDTO){

        BlogPost changedBlogPost = blogDTOConverter.requestDTOToEntity(changedBlogPostDTO);

        BlogPost blogPostOut = blogPostService.updateBlogById(id, changedBlogPost);
        return blogDTOConverter.entityToResponseDTO(blogPostOut);
    }
}