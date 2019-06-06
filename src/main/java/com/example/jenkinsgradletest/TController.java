package com.example.jenkinsgradletest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TController {

    @RequestMapping("/test")
    public String index(){
        return "SUB 05";
    }

    @RequestMapping("/test2")
    public String index2(){ return "SUB 105"; }

    @RequestMapping("/test3")
    public String index3(){ return "SUB 206"; }

    @RequestMapping("/test4")
    public String index4(){ return "SUB 306"; }

    @RequestMapping("/test5")
    public String index5(){ return "SUB 406"; }

    @RequestMapping("/test6")
    public String index6(){ return "SUB 506"; }

    @RequestMapping("/test7")
    public String index7(){ return "SUB 606"; }

    @RequestMapping("/test8")
    public String index8(){ return "SUB 706"; }

}


