package com.example.foodwar.blogs_management;

public class blogEntity {
    String blog_description, blog_name, image_blog, author, publisher;
    int likes;

    public blogEntity() {
    }

    public blogEntity(String blog_description, String blog_name, String image_blog, String author, String publisher) {
        this.blog_description = blog_description;
        this.blog_name = blog_name;
        this.image_blog = image_blog;
        this.author = author;
        this.publisher = publisher;
    }

    public String getBlog_description() {
        return blog_description;
    }

    public void setBlog_description(String blog_description) {
        this.blog_description = blog_description;
    }

    public String getBlog_name() {
        return blog_name;
    }

    public void setBlog_name(String blog_name) {
        this.blog_name = blog_name;
    }

    public String getImage_blog() {
        return image_blog;
    }

    public void setImage_blog(String image_blog) {
        this.image_blog = image_blog;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


}