package com.example.spring_boot_demo.services;

import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    //@Autowired
    BlogPostRepository blogPostRepository;

    public BlogService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }


    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public BlogPost findBlogById(int id) {
        return blogPostRepository.findById(id).orElseThrow();
    }


    public void DeleteById(int id) {
        blogPostRepository.deleteById(id);
    }

    public BlogPost updatBlogById(int id, BlogPost changedBlogPost) {

        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow();



         if(changedBlogPost.getTitle() != null)
                blogPost.setTitle(changedBlogPost.getTitle());
        if (changedBlogPost.getMessage() != null)
                blogPost.setMessage(changedBlogPost.getMessage() );

            return blogPostRepository.save(blogPost);



        //BeanUtils.copyProperties(changedBlogPost, existingBlogPost, "id");




    }

    public BlogPost CreateBlog(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

}

