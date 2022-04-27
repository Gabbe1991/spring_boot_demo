package com.example.spring_boot_demo.views;

import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.services.BlogPostService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class BlogForm extends FormLayout {

    TextField title = new TextField("Title");
    TextArea message = new TextArea("Message");
    Button saveButton = new Button("Save");

    Binder<BlogPost> binder = new BeanValidationBinder<>(BlogPost.class);
    BlogPostService blogPostService;
    MangePostView mangePostView;

    public BlogForm(BlogPostService blogPostService, MangePostView mangePostView){
        this.blogPostService = blogPostService;
        this.mangePostView = mangePostView;
        setVisible(false);
        binder.bindInstanceFields(this);

        saveButton.addClickListener(evt -> onSave());

        add(title, message, saveButton);
    }

    private void onSave() {
        BlogPost blogPost = binder.validate().getBinder().getBean();
        if(blogPost.getId() != 0){
            blogPostService.updateBlogById(blogPost.getId(), blogPost);
        } else {
            blogPostService.createBlog(blogPost);
        }
        setBlogPost(null);
        mangePostView.updateItems();
    }

    public void setBlogPost(BlogPost blogPost){
        if(blogPost != null){
            binder.setBean(blogPost);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }
}