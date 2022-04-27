package com.example.spring_boot_demo.views;

import com.example.spring_boot_demo.services.BlogPostService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("/helloworld")
public class HelloWorldView extends VerticalLayout {
    BlogPostService blogPostService;

    public HelloWorldView(BlogPostService blogPostService){
        this.blogPostService = blogPostService;
        setAlignItems(Alignment.CENTER);

        H1 pageTitle = new H1("Hello World!");
        add(pageTitle);


        renderBlogPost(); 




    }

    private void renderBlogPost() {
        blogPostService.findAll(null).forEach(blogPost -> {


            VerticalLayout blogPostLayout = new VerticalLayout();

            H2 blogTitle = new H2(blogPost.getTitle());
            Paragraph blogMessage = new Paragraph(blogPost.getMessage());

            Button button = new Button("Delete", ev -> {

                blogPostService.deleteById(blogPost.getId());

                Notification.show("Deleted " + blogPost.getId());
                updateBlogPosts();
            });



            blogPostLayout.add(blogTitle, blogMessage, button, new Hr());
            blogPostLayout.setId(String.valueOf(blogPost.getId()));
            blogPostLayout.addClassName("blogpostlayout");

            add(blogPostLayout);

        });
    }

    private void updateBlogPosts() {
        this
                .getChildren()
                .filter(component -> component.getElement().getClassList().contains("blogpostlayout"))
                        .forEach(component -> remove(component));
        renderBlogPost();

    }
}