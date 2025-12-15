package com.jpdsiicourse.zwierze;

import com.jpdsii.dao.UserDAO;
import com.jpdsii.entities.User;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;

@Named
@SessionScoped
public class AuthBB implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    @Inject
    private UserDAO userDAO;

    @Inject
    private UserSessionBB userSession; 


    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }



    public boolean isLoggedIn() {
        return userSession.isLoggedIn();
    }

    public String login() {
        User user = userDAO.findByUsername(username);

        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie znaleziono użytkownika", null)
            );
            return null;
        }

        if (!user.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawne hasło", null)
            );
            return null;
        }


        userSession.setUsername(user.getUsername());
        userSession.setRole("USER"); 
        userSession.setLanguage("pl"); 

        return "/index.xhtml?faces-redirect=true";
    }

    public String logout() {

        userSession.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}
