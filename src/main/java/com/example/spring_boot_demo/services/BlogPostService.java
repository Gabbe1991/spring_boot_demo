package com.example.spring_boot_demo.services;

import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.repositories.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    //@Autowired
    //BlogPostRepository blogPostRepository;

    BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }


    public List<BlogPost> findAll(String username) {
        if (username != null) {
            return blogPostRepository.findByAppUser_Username(username);
        } else {
            return blogPostRepository.findAll();
        }
    }

    public BlogPost findBlogById(int id) {
        return blogPostRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {
        blogPostRepository.deleteById(id);
    }

    public BlogPost createBlog(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updateBlogById(int id, BlogPost changedBlogPost) {

        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow();


        if (changedBlogPost.getTitle() != null)
            blogPost.setTitle(changedBlogPost.getTitle());
        if (changedBlogPost.getMessage() != null)
            blogPost.setMessage(changedBlogPost.getMessage());

        return blogPostRepository.save(blogPost);


        //BeanUtils.copyProperties(changedBlogPost, existingBlogPost, "id");
    }

    public void save(BlogPost blogPost) {
        blogPostRepository.save(blogPost);
    }
}