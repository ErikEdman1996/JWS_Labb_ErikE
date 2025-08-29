package org.example.jws_labb_erike.services;

import org.example.jws_labb_erike.entities.Post;

import java.util.List;

public interface PostServiceInterface
{
    Post addPost(Post post);
    List<Post> getAllPosts();
}
