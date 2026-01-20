package com.mn.forum.bb;

import com.mn.forum.dao.PostDAO;
import com.mn.forum.entities.Post;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class MyPostsBB {

    @Inject
    private PostDAO postDAO;

    @Inject
    private AuthBB authBB;

    public List<Post> getMyPosts() {
        if (authBB.isAdmin()) {
            return postDAO.findAll(); // ADMIN бачить всі пости
        }
        return postDAO.findByAuthor(authBB.getLoggedUser());
    }

    public void delete(Integer postId) {
        Post post = postDAO.findById(postId);

        if (post == null) {
            return;
        }

        if (authBB.isAdmin()) {         // ADMIN може все
            postDAO.deleteById(postId);
            return;
        }

        if (post.getAuthor().getId().equals(authBB.getLoggedUser().getId())) { // USER може тільки свої
            postDAO.deleteById(postId);
        }
    }

    public boolean canDelete(Post post) {
        return authBB.isAdmin()
                || post.getAuthor().getId().equals(authBB.getLoggedUser().getId());
    }
}
