package com.nnw.game.server;

import lombok.AllArgsConstructor;
import lombok.Data;


public class LoginLocalCredentials {
    private String username;

    private String password;

    //getters and setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginLocalCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
