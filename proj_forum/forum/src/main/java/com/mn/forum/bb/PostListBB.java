package com.mn.forum.bb;

import com.mn.forum.dao.PostDAO;
import com.mn.forum.entities.Post;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class PostListBB implements Serializable {

    @Inject
    private PostDAO postDAO;

    private String search;
    private List<Post> posts;

    

    public void load() {
        posts = postDAO.findFiltered(search);
    }

    public List<Post> getPosts() {
        if (posts == null) {
            posts = postDAO.findFiltered(search);
        }
        return posts;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
