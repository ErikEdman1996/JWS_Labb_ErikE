package org.example.jws_labb_erike.services;

import org.example.jws_labb_erike.entities.Post;

import java.util.List;

public interface PostServiceInterface
{
    Post addPost(Post post);
    Post getPostById(Long id);
    Post updatePost(Post post, String email);
    void deletePost(Long id, String email);
    List<Post> getAllPosts();
    List<Post> getAllPostsByEmail(String email);
    Long getPostCount();
}
