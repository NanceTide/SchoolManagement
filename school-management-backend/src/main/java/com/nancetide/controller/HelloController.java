package com.nancetide.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello Security");
        return "Hello Security";
    }

    @RequestMapping("/index")
    public String index() {
        System.out.println("Hello Index");
        return "Hello Index";
    }

}
