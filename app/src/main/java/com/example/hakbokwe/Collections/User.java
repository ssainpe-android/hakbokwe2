/*
https://cloud.google.com/firestore/docs/samples/firestore-query-collection-group-dataset?hl=ko
 */

package com.example.hakbokwe.Collections;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
