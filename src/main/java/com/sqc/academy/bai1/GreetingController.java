package com.sqc.academy.bai1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hãy xây dựng một API đơn giản cho phép người dùng gửi tên của họ và nhận lại một câu chào mừng.
 */
@RestController
@RequestMapping("/bai1")
public class GreetingController {
    @RequestMapping("/greeting")
    public String hello(@RequestParam(defaultValue = "World") String name) {
        return "Hello " + name + "!";
    }
}
