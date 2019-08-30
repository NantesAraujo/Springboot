package br.com.alura.forum.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @ResponseBody
    @RequestMapping(path = "/")
    public String helloWold(){
        return "Hello World Api Forum testeteste";
    }
}
