package com.example.restapi.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}") // GET /user/1 => 1 is String But Converted
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null) {

            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) { // @Valid에 위반되는 body라면 400 error
        User newUser = service.save(user);
        // 이전 요청의 URI를 (/users) 재사용 하는 UriComponentsBuilder를 상속하는 클래스
        // URI를 생성해주는 기능
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).build(); // URI 요청 결과를 체크해주는 기능 (상태코드)
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        User deletedUser = service.deleteById(id);

        if (deletedUser == null) throw new UserNotFoundException(String.format("ID[%s] not found", id));
        return deletedUser;
    }
}
