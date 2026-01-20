package com.mn.forum.bb;

import com.mn.forum.dao.PostDAO;
import com.mn.forum.entities.Post;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CreatePostBB {

    private String title;
    private String subtitle;
    private String content;

    @Inject
    private PostDAO postDAO;

    @Inject
    private AuthBB authBB;

    public String publish() {

        Post post = new Post();
        post.setTitle(title);
        post.setSubtitle(subtitle);
        post.setContent(content);
        post.setCreatedAt(new java.util.Date());
        post.setAuthor(authBB.getLoggedUser());

        postDAO.create(post);

        return "/pages/myPosts.xhtml?faces-redirect=true";
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
