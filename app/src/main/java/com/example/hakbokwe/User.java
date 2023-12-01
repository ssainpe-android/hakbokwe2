/*
https://cloud.google.com/firestore/docs/samples/firestore-query-collection-group-dataset?hl=ko
 */

package com.example.hakbokwe;

public class User {
    private String name;
    private String id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
