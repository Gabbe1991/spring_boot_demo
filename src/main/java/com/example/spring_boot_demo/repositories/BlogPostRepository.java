package com.example.spring_boot_demo.repositories;

import com.example.spring_boot_demo.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost,Integer> {
}
