package com.spectrabhi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from SpectrAbhi!";
    }

    @GetMapping("/status")
    public String status() {
        return "App is running fine !";
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/sum")
    public String sum(@RequestParam int a, @RequestParam int b) {
        return "Sum is: " + (a + b);
    }

}
