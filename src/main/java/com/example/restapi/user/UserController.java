package com.example.restapi.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/v1/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);

        mapping.setFilters(filters);

        return mapping;

    }

    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(model);

        mapping.setFilters(filters);

        return mapping;
    }

//    ?????? ?????????????????? uri ??? version ????????? ?????? ?????? (Twitter, Amazone)
//    @GetMapping("/users/{id}") => GET /users/1 => 1 is String But Converted
//    @GetMapping("/v1/users/{id}") => GET /v1/users/1

//    ????????? ???, header??? version ????????? ???????????? ????????? ?????? (Github, Microsoft)
//    @GetMapping(value="/users/{id}", params="version=1") => GET /users/1?version=1
//    @GetMapping(value="/users/{id}", headers="X-API-VERSION=1") => headers include X-API-VERSION

//    headers include [Accept = application/vnd.company.appv1+json] (mime-type version handling)
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);

        mapping.setFilters(filters);

        return mapping;
    }

//    @GetMapping("/v2/users/{id}") => GET /v2/users/1
//    @GetMapping(value="/users/{id}", params="version=2") => GET /users/1?version=2
//    @GetMapping(value="/users/{id}", headers="X-API-VERSION=2")

    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");

        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(userV2);

        mapping.setFilters(filters);

        return mapping;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) { // @Valid??? ???????????? body?????? 400 error
        User newUser = service.save(user);
        // ?????? ????????? URI??? (/users) ????????? ?????? UriComponentsBuilder??? ???????????? ?????????
        // URI??? ??????????????? ??????
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).build(); // URI ?????? ????????? ??????????????? ?????? (????????????)
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        User deletedUser = service.deleteById(id);

        if (deletedUser == null) throw new UserNotFoundException(String.format("ID[%s] not found", id));
        return deletedUser;
    }
}
