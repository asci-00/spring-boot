package com.example.restapi.post;

import com.example.restapi.user.User;
import com.example.restapi.user.UserNotFoundException;
import com.example.restapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class PostJpaController {
    @Autowired
    private PostRepository postRepo;

    @GetMapping("/posts")
    public List<Post> retrieveAllPosts() {
        List<Post> posts = postRepo.findAll();

        return posts;
    }

    @GetMapping("/posts/{id}")
    public Post retrievePost(@PathVariable int id) {
        Optional<Post> post = postRepo.findById(id);

        if(!post.isPresent()) throw new UserNotFoundException(String.format("ID[%s] not found", id));

        return post.get();
    }
}
