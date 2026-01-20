package com.mn.forum.bb;

import com.mn.forum.dao.UserDAO;
import com.mn.forum.entities.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;

@Named
@SessionScoped
public class AuthBB implements Serializable {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private User loggedUser;

    @Inject
    private UserDAO userDAO;

    public String login() {

        User user = userDAO.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        loggedUser = user;

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
                (HttpServletRequest) ctx.getExternalContext().getRequest();

        RemoteClient<User> client =
                new RemoteClient<>(username, null,
                        user.getFirstName() + " " + user.getLastName(),
                        request);

        client.setDetails(user);
        client.getRoles().add(user.getRole().getName());
        client.store(request);

        return "/pages/index.xhtml?faces-redirect=true";
    }

    public String logout() {

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        loggedUser = null;
        username = null;
        password = null;

        return "/pages/login.xhtml?faces-redirect=true";
    }

    public String checkLogin() {
        if (!isLoggedIn()) {
            return "/pages/login.xhtml?faces-redirect=true";
        }
        return null;
    }

    public boolean isLoggedIn() {
        return loggedUser != null;
    }

    public boolean isAdmin() {
        return isLoggedIn() && "ADMIN".equals(loggedUser.getRole().getName());
    }

    public String register() {

        if (userDAO.existsByUsername(username)) {
            return null;
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setCreatedAt(new java.util.Date());
        user.setRole(userDAO.findRoleByName("USER"));

        userDAO.create(user);

        return "/pages/login.xhtml?faces-redirect=true";
    }

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

    public User getLoggedUser() {
        return loggedUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
