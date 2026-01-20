package com.mn.forum.bb;

import com.mn.forum.dao.PostDAO;
import com.mn.forum.entities.Post;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EditPostBB implements Serializable {

    @Inject
    private PostDAO postDAO;

    private Integer id;
    private Post post;

    public void load() {
        if (id != null && post == null) {
            post = postDAO.findById(id);
        }
    }

    public String save() {
        post.setUpdatedAt(new java.util.Date());
        postDAO.update(post);
        return "/pages/myPosts.xhtml?faces-redirect=true";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }
}
