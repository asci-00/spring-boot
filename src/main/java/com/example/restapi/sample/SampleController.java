package com.example.restapi.controllers;

import com.example.restapi.beans.TestBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController { // GET /test (endpoint)
    // @GetMapping(path = "/test-request")
    // @RequestMapping(method=RequestMethod.GET, path="/test-request")
    @GetMapping("/test-request/{number}")
    public String test(@PathVariable int number) {
        return "Test Response " + number;
    }

    // spring bean 형태로 handler 작성 시, JSON 형태로 반환됨
    @GetMapping("/test-bean")
    public TestBean testBean() {
        return new TestBean("Test Response");
    }
}
