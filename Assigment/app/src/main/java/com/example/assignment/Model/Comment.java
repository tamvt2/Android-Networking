package com.example.assignment.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Comment {
    String id_story, id_user, content, date, id;

    public Comment (JSONObject obj) throws JSONException {
        id = obj.getString("_id");
        id_story = obj.getString("id_story");
        id_user = obj.getString("id_user");
        content = obj.getString("content");
        date = obj.getString("date");
    }

    public Comment(String id_story, String id_user, String content, String date, String id) {
        this.id = id;
        this.id_story = id_story;
        this.id_user = id_user;
        this.content = content;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_story() {
        return id_story;
    }

    public void setId_story(String id_story) {
        this.id_story = id_story;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
