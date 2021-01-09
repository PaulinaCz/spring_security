package com.czerniecka.spring_security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(){
        return "main";
    } // Spring MVC -> Spring looking for view "view"

    @PostMapping("/test")
    @ResponseBody
    // @CrossOrigin("http://127.0.0.1:8080")
    public String test(){
        return "TEST!";
    } // @Controller + @ResponseBody = @RestController
    // Spring doesn't look for view "view_name"

}
