package com.example.spring_boot_demo.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/vaadinblog", layout = AppView.class )
public class BlogView extends VerticalLayout {


        public BlogView(){
            add(new H1("Blog-viewen är här"));


        }






}
