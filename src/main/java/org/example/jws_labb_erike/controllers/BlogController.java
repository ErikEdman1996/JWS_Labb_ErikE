package org.example.jws_labb_erike.controllers;

import org.example.jws_labb_erike.entities.Post;
import org.example.jws_labb_erike.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/newpost")
    public ResponseEntity<Post> addNewPost(@RequestBody Post post, JwtAuthenticationToken token)
    {
        String keycloakSub = token.getToken().getSubject();
        post.setKeycloakSub(keycloakSub);

        Post createdPost = postService.addPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/posts")
    public ResponseEntity<String> getPosts()
    {
        String s = "Test string...";
        return ResponseEntity.ok(s);
    }
}
