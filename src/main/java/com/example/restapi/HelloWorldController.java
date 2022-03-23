package com.example.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController { // GET /test (endpoint)
    // @GetMapping(path = "/test-request")
    // @RequestMapping(method=RequestMethod.GET, path="/test-request")
    @GetMapping("/test-request/{number}")
    public String test(@PathVariable int number) {
        return "Test Response " + number;
    }
}
