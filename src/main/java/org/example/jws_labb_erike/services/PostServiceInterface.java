package org.example.jws_labb_erike.services;

import org.example.jws_labb_erike.entities.Post;

import java.util.List;

public interface PostServiceInterface
{
    Post addPost(Post post);
    Post getPostById(Long id);
    List<Post> getAllPosts();
    List<Post> getAllPostsByKeycloakSub(String keycloakSub);
    Long getPostCount();
}
