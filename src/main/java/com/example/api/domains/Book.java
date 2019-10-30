package com.example.api.domains;

import java.util.List;

public class Book {

    private String title;
    private List<String> authors;
    private String kind;

    public Book() {
    }

    public Book(String title, List<String> authors, String kind) {
        this.title = title;
        this.authors = authors;
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
