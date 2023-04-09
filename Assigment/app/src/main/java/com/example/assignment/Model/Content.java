package com.example.assignment.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Content {
    private String url;

    public Content(JSONObject obj) throws JSONException {
        url = obj.getString("img");
    }

    public Content(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
