package com.br.pedroarruda.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping("/TelaMenu")
    public String TelaMenu(){
        return "TelaMenu";
    }


}
