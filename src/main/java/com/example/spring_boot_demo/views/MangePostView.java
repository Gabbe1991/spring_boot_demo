package com.example.spring_boot_demo.views;


import com.example.spring_boot_demo.entities.BlogPost;
import com.example.spring_boot_demo.services.BlogPostService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;


@Route(value = "/managepost", layout = AppView.class )
public class MangePostView extends VerticalLayout {


    BlogPostService blogPostService;


        public MangePostView(BlogPostService blogPostService){
            this.blogPostService = blogPostService;
            setAlignItems(Alignment.CENTER);
            add(new H1("Manage post view"));

            Grid<BlogPost> grid = new Grid<>(BlogPost.class, false);
            grid.setItems(blogPostService.findAll(null));

            grid.addColumn(BlogPost::getId).setHeader("Id").setSortable(true);
            grid.addColumn(BlogPost::getTitle).setHeader("Title").setSortable(true);
            grid.addColumn(BlogPost::getMessage).setHeader("Message").setSortable(true);
            grid.addColumn(blogPost -> blogPost.getAppUser().getUsername()).setHeader("Author").setSortable(true);

            add(grid);


        }


}
