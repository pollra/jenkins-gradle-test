package com.example.jenkinsgradletest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TController {

    @RequestMapping("/test")
    public String index(){
        return "SUB 05";
    }

    @RequestMapping("/test02")
    public String index2(){ return "SUB 105"; }
}


