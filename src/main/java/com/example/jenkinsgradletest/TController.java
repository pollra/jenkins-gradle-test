package com.example.jenkinsgradletest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TController {

    @RequestMapping("/")
    public String index(){
        return "ㅎㅇ?";
    }

    @RequestMapping("/test")
    public String testPage01(){
        return "ㅇㅅㅇ";
    }

}


