package com.example.assignment.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String username, fullname, email, id;

    public User(JSONObject obj) throws JSONException {
        id = obj.getString("_id");
        username = obj.getString("username");
        fullname = obj.getString("fullname");
        email = obj.getString("email");
    }

    public User(String username, String fullname, String email, String id) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
