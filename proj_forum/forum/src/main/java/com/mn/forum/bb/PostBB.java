package com.mn.forum.bb;

import com.mn.forum.dao.PostDAO;
import com.mn.forum.entities.Post;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PostBB {

    @Inject
    private PostDAO postDAO;

    public List<Post> getAllPosts() {
        return postDAO.findAll();
    }
}
