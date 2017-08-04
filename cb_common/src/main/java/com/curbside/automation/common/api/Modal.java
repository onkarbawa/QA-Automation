package com.curbside.automation.common.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hitesh.grover on 04/08/17.
 */
public class Modal {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
