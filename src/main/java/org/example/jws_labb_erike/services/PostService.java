package org.example.jws_labb_erike.services;

import org.example.jws_labb_erike.entities.Post;
import org.example.jws_labb_erike.exceptions.ResourceNotFoundException;
import org.example.jws_labb_erike.exceptions.UnauthorizedActionException;
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

        if(post.isPresent())
        {
            return post.get();
        }

        throw new ResourceNotFoundException("Post", "id", id);
    }

    @Override
    public Post updatePost(Post post, String email)
    {
        Optional<Post> postToUpdate = postRepository.findById(post.getId());

        if(postToUpdate.isPresent() && postToUpdate.get().getEmail().equals(email))
        {
            return postRepository.save(post);
        }
        else if(postToUpdate.isPresent() && !postToUpdate.get().getEmail().equals(email))
        {
            throw new UnauthorizedActionException("You are not allowed to update this post.");
        }

        throw new ResourceNotFoundException("Post", "id", post.getId());
    }

    @Override
    public void deletePost(Long id, String email)
    {
        Optional<Post> postToDelete = postRepository.findById(id);

        if(postToDelete.isPresent() && postToDelete.get().getEmail().equals(email))
        {
            postRepository.deleteById(id);
            return;
        }
        else if(postToDelete.isPresent() && !postToDelete.get().getEmail().equals(email))
        {
            throw new UnauthorizedActionException("You are not allowed to delete this post.");
        }

        throw new ResourceNotFoundException("Post", "id", id);
    }

    @Override
    public List<Post> getAllPosts()
    {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllPostsByEmail(String email)
    {
        return postRepository.findAllByEmail(email);
    }

    @Override
    public Long getPostCount()
    {
        return postRepository.count();
    }
}
