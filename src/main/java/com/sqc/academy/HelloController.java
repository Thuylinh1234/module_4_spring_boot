package com.sqc.academy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/greeting") // API, endpint
    public String hello(@RequestParam(defaultValue = " ") String name, @RequestParam(defaultValue = "Đà Nẵng") String diaChi){
       return "Hello " + name + " " + diaChi + "!";
    }
}
