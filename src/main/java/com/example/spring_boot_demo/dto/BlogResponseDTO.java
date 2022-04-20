package com.example.spring_boot_demo.dto;

public class BlogResponseDTO {

    private int id;
    private String title;
    private String message;
    private int appUserDd;

    public BlogResponseDTO(int id) {
        this.id = id;
    }

    public BlogResponseDTO(int id, String title, String message, int appUserDd) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.appUserDd = appUserDd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAppUserDd() {
        return appUserDd;
    }

    public void setAppUserDd(int appUserDd) {
        this.appUserDd = appUserDd;
    }
}


