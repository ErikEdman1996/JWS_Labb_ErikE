package org.example.jws_labb_erike.entities;

import jakarta.persistence.*;

@Entity
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String keycloakSub;

    @Column(length = 40, nullable = false)
    private String title;

    @Column
    private String content;

    public Post()
    {

    }

    public Post(Long id, String keycloakSub, String title, String content) {
        this.id = id;
        this.keycloakSub = keycloakSub;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeycloakSub() {
        return keycloakSub;
    }

    public void setKeycloakSub(String keycloakSub) {
        this.keycloakSub = keycloakSub;
    }
}
