package org.example.task4;

public class Beatmap {
    String name;
    String author;
    String mapper;
    String status;

    public Beatmap(String name, String author, String mapper, String status) {
        this.name = name;
        this.author = author;
        this.mapper = mapper;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getMapper() {
        return mapper;
    }

    public String getStatus() {
        return status;
    }
}
