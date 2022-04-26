package com.example.spring_boot_demo.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/helloworld")
public class HelloWorldView extends VerticalLayout {

    public HelloWorldView(){
        setAlignItems(Alignment.CENTER);

        H1 pageTitle = new H1("Hello World!");
        add(pageTitle);
    }
}