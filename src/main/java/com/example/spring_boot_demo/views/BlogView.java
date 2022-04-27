package com.example.spring_boot_demo.views;

import com.example.spring_boot_demo.entities.AppUser;
import com.example.spring_boot_demo.services.BlogPostService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

@Route(value = "/vaadinblog", layout = AppView.class )
public class BlogView extends VerticalLayout {

    BlogPostService blogPostService;

    public BlogView(BlogPostService blogPostService){
        this.blogPostService = blogPostService;

        setAlignItems(Alignment.CENTER);

        blogPostService.findAll().forEach(blogPost -> {

            //Added - If no AppUser on blogpost state "from unknown" and return empty email as to not error
            AppUser appUser = blogPost.getAppUser();

            H2 blogTitle;
            Paragraph blogEmail;

            if (appUser == null){
                blogTitle = new H2(blogPost.getTitle() + " from unknown");
                blogEmail = new Paragraph("");
            } else {
                blogTitle = new H2(blogPost.getTitle() + " from " + blogPost.getAppUser().getUsername());
                blogEmail = new Paragraph(blogPost.getAppUser().getEmail());
            }


            Paragraph blogMessage = new Paragraph(blogPost.getMessage());

            add(blogTitle, blogMessage, blogEmail, new Hr());
        });
    }
}