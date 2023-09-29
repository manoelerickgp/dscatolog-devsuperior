package com.project.dscatolog.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/testMethod")
    public ResponseEntity<Object> test(){
        String test = "Hi, this is just a test, Thanks!";
        return ResponseEntity.ok(test);
    }
}
