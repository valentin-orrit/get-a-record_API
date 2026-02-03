package com.getarecord.getarecord.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiViewController {
    @GetMapping({"/api", "/api/"})
    public String apiIndex() {
        return "redirect:/api/index.html";
    }
}
