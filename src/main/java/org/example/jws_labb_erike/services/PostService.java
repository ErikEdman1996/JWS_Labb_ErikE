package org.example.jws_labb_erike.services;

import org.example.jws_labb_erike.entities.Post;
import org.example.jws_labb_erike.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Post getPostById(Long id)
    {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseGet(() -> postRepository.findById(id).get());
    }

    @Override
    public List<Post> getAllPosts()
    {
        return List.of();
    }

    @Override
    public List<Post> getAllPostsByKeycloakSub(String keycloakSub)
    {
        //TODO: Kolla om användaren finns

        List<Post> posts = postRepository.findByKeycloakSub(keycloakSub);

        return posts;
    }

    @Override
    public Long getPostCount()
    {
        return postRepository.count();
    }
}
