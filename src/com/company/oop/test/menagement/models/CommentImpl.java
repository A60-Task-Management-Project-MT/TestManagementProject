package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Comment;

public class CommentImpl implements Comment {

    private String author;
    private String content;

    public CommentImpl(String author, String content) {
        this.author = author;
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return """
                **********
                %s
                User: %s
                **********
                """.formatted(getContent(), getAuthor()).trim();
    }
}
