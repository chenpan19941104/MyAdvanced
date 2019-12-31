package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class DemoController {
    private static final String a = "";

    public static void main(String[] args) {
        System.out.println("111");

    }

    @GetMapping("/demo")
    @ResponseBody
    public String Hello() {
        ArrayList<Object> objects = new ArrayList<>();
        List list;
        
        return "hello";
    }
}
