package com.spring.delivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class ServerController {

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> status() {
        return ResponseEntity.ok().build();
    }
}
