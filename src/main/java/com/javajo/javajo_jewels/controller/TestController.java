package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.request.Test1;
import com.javajo.javajo_jewels.response.Test2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/")
public class TestController {

    @RequestMapping("hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("test")
    public Test2 test(@RequestBody Test1 test) {
        String testStr = test.getTest1();
       System.out.println(testStr);
       Test2 test2 = new Test2();
       test2.setTest1(testStr);
       test2.setTest2("Hello");
        return test2;
    }
}
