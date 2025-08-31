package org.example.jws_labb_erike.controllers;

import org.example.jws_labb_erike.entities.Post;
import org.example.jws_labb_erike.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class BlogController
{
    private final PostService postService;

    @Autowired
    public BlogController(final PostService postService)
    {
        this.postService = postService;
    }

    /*User endpoints*/

    @PostMapping("/newpost")
    public ResponseEntity<Post> addNewPost(@RequestBody Post post, JwtAuthenticationToken token)
    {
        String keycloakSub = token.getToken().getSubject();
        post.setKeycloakSub(keycloakSub);

        Post createdPost = postService.addPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts(JwtAuthenticationToken token)
    {
        String keycloakSub = token.getToken().getSubject();
        List<Post> posts = postService.getAllPostsByKeycloakSub(keycloakSub);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id)
    {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    /*Admin endpoints*/
    @GetMapping("/count")
    public ResponseEntity<String> getPostCount()
    {
        Long count = postService.getPostCount();
        String response = "The post count is: "+ count;

        return ResponseEntity.ok(response);
    }
}
