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

    /*Authenticated endpoints*/
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost()
    {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id)
    {
        Post post = postService.getPostById(id);

        return ResponseEntity.ok(post);
    }

    /*User endpoints*/
    @PostMapping("/newpost")
    public ResponseEntity<Post> addNewPost(@RequestBody Post post, JwtAuthenticationToken token)
    {
        String keycloakSub = token.getToken().getSubject();
        String email = token.getToken().getClaim("email").toString();

        post.setEmail(email);

        Post createdPost = postService.addPost(post);

        System.out.println("The current keycloak sub is: " + keycloakSub);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/updatepost")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, JwtAuthenticationToken token)
    {
        String email = token.getToken().getClaim("email").toString();

        return ResponseEntity.ok(postService.updatePost(post, email));
    }

    @DeleteMapping("/deletepost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, JwtAuthenticationToken token)
    {
        String email = token.getToken().getClaim("email").toString();
        postService.deletePost(id, email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
