package com.example.restapi.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class SampleController { // GET /test (endpoint)
    @Autowired
    private MessageSource languageSource;

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

    @GetMapping("/test-internationalized")
    public String testInternationalized(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale
    ) {
        return languageSource.getMessage("greeting.message", null, locale);
    }
}
