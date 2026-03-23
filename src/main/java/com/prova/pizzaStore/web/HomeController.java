package com.prova.pizzaStore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/home",""})
    public String loginMessagge(){return "index";}
}
