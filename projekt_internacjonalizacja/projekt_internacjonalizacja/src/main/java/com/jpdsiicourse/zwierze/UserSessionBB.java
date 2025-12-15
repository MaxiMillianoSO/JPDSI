package com.jpdsiicourse.zwierze;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("userSession")
@SessionScoped
public class UserSessionBB implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String role;
    private String language = "pl"; 



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public boolean isLoggedIn() {
        return username != null;
    }

    public void invalidate() {
        username = null;
        role = null;
        language = "pl";
    }
}
