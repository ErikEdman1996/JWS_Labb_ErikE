package org.example.jws_labb_erike.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class BlogController
{
    @GetMapping("/posts")
    public ResponseEntity<String> getPosts()
    {
        String s = "Test string...";
        return ResponseEntity.ok(s);
    }
}
