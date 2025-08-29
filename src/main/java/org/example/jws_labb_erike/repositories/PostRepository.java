package org.example.jws_labb_erike.repositories;

import org.example.jws_labb_erike.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long>
{
    List<Post> findByKeycloakSub(String keycloakSub);
}
