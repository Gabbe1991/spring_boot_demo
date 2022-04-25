package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.dto.BlogDTOConverter;
import com.example.spring_boot_demo.dto.BlogRequestDTO;
import com.example.spring_boot_demo.dto.BlogResponseDTO;
import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.repositories.BlogPostRepository;
import com.example.spring_boot_demo.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    BlogPostRepository blogPostRepository;


    @Autowired
    BlogPostService blogService;


    @Autowired
    BlogDTOConverter blogDTOConverter;


    @GetMapping
    public String getBlogPostList(@RequestParam(required = false)String username, Model model){
         List<BlogPost> blogPostList =blogService.findAll(username);
        model.addAttribute("blogPostList", blogPostList);
         return "blog";

    }
    @GetMapping("addBlog")
    public String addBlog(){
        return "addBlogForm";
    }

    @GetMapping("{id}")
    public BlogResponseDTO getBlogPostById(@PathVariable("id") int id) {
        BlogPost blogPost = blogService.findBlogById(id);
        return blogDTOConverter.entityToResponseDTO((blogPost));
    }

    @PutMapping("/{id}")
    public BlogResponseDTO  updateByBlogPostId(
            @PathVariable("id")int id,
            @RequestBody BlogRequestDTO changedBlogPostDTO){

        BlogPost changedBlogPost = blogDTOConverter.requestDTOToEntity(changedBlogPostDTO);

        BlogPost blogPostOut = blogService.updateBlogById(id, changedBlogPost);
        return blogDTOConverter.entityToResponseDTO(blogPostOut);


       // return blogService.updatBlogById(id, changedBlogPost);

    }






 /*   @DeleteMapping("/{id}")
    public void deleteBlogPostById(@PathVariable("id") int id){
         blogService.DeleteById(id);
    }

    @PostMapping
    public BlogResponseDTO createNewBlogPost(@RequestBody BlogRequestDTO blogRequestDTO){
        BlogPost blogPostIn = blogDTOConverter.requestDTOToEntity(blogRequestDTO);

        BlogPost blogPostOut = blogService.CreateBlog(blogPostIn);
        return blogDTOConverter.entityToResponseDTO((blogPostOut));

    }

 */
//i must swap diz cuz it do nathing
   /* @PostMapping
    public String editBlogPost(@PathVariable("id") int id, @ModelAttribute BlogPost blogPost){
    blogPostRepository.updatebyId(id, blogPost);
    return "redirect:/blog"; */

    @PostMapping
    public String createBlog(@ModelAttribute BlogPost blogPost){
        blogPostService.save(blogPost);
                return "redirect:/blog";
    }
    


}


