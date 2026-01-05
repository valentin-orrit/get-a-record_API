package com.getarecord.getarecord.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${lastfm.api.sharedSecret}")
    private String lastfmSharedSecret;

    @GetMapping("/api/hello")
    public String hello() {
        return String.format("hello ! \nlast.fm shared secret is %s.", lastfmSharedSecret);
    }
}
