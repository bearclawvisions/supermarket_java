package com.bearclawvisions.api.controllers;

import com.bearclawvisions.api.annotations.IsAdmin;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@IsAdmin
@RestController
@RequestMapping(value = "/coffee", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class CoffeeController {

    @GetMapping("/orderCoffee")
    public String orderCoffee() {
        return "Coffee!";
    }
}
