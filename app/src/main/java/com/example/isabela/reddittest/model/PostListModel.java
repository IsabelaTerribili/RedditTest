package com.example.isabela.reddittest.model;

import com.example.isabela.reddittest.PostModel;

import java.util.List;

/**
 * Created by isabela on 29/01/2018.
 */

public class PostListModel {

    private String postListAfter;

    public PostListModel(String postListAfter) {
        this.postListAfter = postListAfter;
    }

    public String getPostListAfter() {
        return postListAfter;
    }

}