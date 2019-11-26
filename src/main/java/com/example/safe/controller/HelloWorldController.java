package com.example.safe.controller;

import com.example.safe.utils.RequestLimit;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tbw
 * @Date: 2019/11/21 12:33
 */
@RestController
@RequestMapping("test")
public class HelloWorldController {

    @GetMapping("/hello")
    @RequestLimit(count=10,time=60000)
    public String getHello(HttpServletRequest request) {
         return "hello";
    }
}
