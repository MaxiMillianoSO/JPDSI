package com.mn.forum.bb;

import com.mn.forum.dao.PostDAO;
import com.mn.forum.entities.Post;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class PostViewBB {

    @Inject
    private PostDAO postDAO;

    private Post post;

    public Post getPost() {
        if (post == null) {
            String id = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap()
                    .get("id");

            post = postDAO.findById(Integer.valueOf(id));
        }
        return post;
    }
}
