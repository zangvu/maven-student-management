package com.giangvt.unittest.junit5;

public class ArticleManager {
    private User user;
    private ArticleDatabase database;

    public ArticleManager(User user, ArticleDatabase database) {
        this.user = user;
        this.database = database;
    }
}

class User {
}

class ArticleDatabase {
}