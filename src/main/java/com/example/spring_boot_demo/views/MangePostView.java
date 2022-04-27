package com.example.spring_boot_demo.views;


import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.services.BlogPostService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;


@Route(value = "/managepost", layout = AppView.class )
public class MangePostView extends VerticalLayout {


    BlogPostService blogPostService;
    Grid<BlogPost> grid = new Grid<>(BlogPost.class, false);
    BlogForm blogForm;

    public MangePostView(BlogPostService blogPostService){
        this.blogPostService = blogPostService;
        blogForm = new BlogForm(blogPostService, this);
        setAlignItems(Alignment.CENTER);
        add(new H1("Manage post view"));


        grid.setItems(blogPostService.findAll());

        grid.addComponentColumn(blogPost -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.CLOSE), evt ->{
                blogPostService.deleteById(blogPost.getId());
                updateItems();
            });

            deleteButton.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL,
                    ButtonVariant.LUMO_ERROR
            );

            return deleteButton;
        });

        grid.addColumn(BlogPost::getId).setHeader("Id").setSortable(true);
        grid.addColumn(BlogPost::getTitle).setHeader("Title").setSortable(true);
        grid.addColumn(BlogPost::getMessage).setHeader("Message").setSortable(true);
        //grid.addColumn(blogPost -> blogPost.getAppUser().getUsername()).setHeader("Author").setSortable(true);
        grid.asSingleSelect().addValueChangeListener(evt -> {
            blogForm.setBlogPost(evt.getValue());
        });


        HorizontalLayout main = new HorizontalLayout(grid, blogForm);
        main.setSizeFull();

        add(main);

        Button button = new Button("Add new blogpost", evt -> {
            Dialog dialog = new Dialog();
            BlogForm blogForm = new BlogForm(blogPostService, this);
            blogForm.setBlogPost(new BlogPost());
            dialog.add(blogForm);
            dialog.open();
        });
        add(button);
    }

    public void updateItems() {
        grid.setItems(blogPostService.findAll());
    }
}