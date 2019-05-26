package com.example.jenkinsgradletest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TController {

    @RequestMapping("/test")
    public String index(){
        return "ㅇㅅㅇ";
    }
}
