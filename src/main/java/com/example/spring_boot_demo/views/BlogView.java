package com.example.spring_boot_demo.views;

import com.example.spring_boot_demo.services.BlogPostService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/vaadinblog", layout = AppView.class )
public class BlogView extends VerticalLayout {

    BlogPostService blogPostService;

    public BlogView(BlogPostService blogPostService){
        this.blogPostService = blogPostService;

        setAlignItems(Alignment.CENTER);

        blogPostService.findAll().forEach(blogPost -> {
            H2 blogTitle = new H2(blogPost.getTitle());
            Paragraph blogMessage = new Paragraph(blogPost.getMessage());

            add(blogTitle, blogMessage, new Hr());
        });
    }
}