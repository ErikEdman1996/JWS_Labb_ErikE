package org.example.jws_labb_erike.services;

import org.example.jws_labb_erike.entities.Post;
import org.example.jws_labb_erike.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements PostServiceInterface
{
    private final PostRepository postRepository;

    @Autowired
    public PostService(final PostRepository postRepository)
    {
        this.postRepository = postRepository;
    }

    @Override
    public Post addPost(Post post)
    {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts()
    {
        return List.of();
    }
}
