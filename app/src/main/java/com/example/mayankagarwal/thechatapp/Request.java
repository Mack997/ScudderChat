package com.example.mayankagarwal.thechatapp;

/**
 * Created by Mayank Agarwal on 10-02-2018.
 */

public class Request {
    String name, status, thumb_image;
    public Request(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumb_image() {
        return thumb_image;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }

    public Request(String name, String status, String thumb_image) {

        this.name = name;
        this.status = status;
        this.thumb_image = thumb_image;
    }
}
