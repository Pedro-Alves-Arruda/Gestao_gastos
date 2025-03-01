package com.br.pedroarruda.ssm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.pedroarruda.ssm.configuration.ParameterStoreConfiguration;

@RestController
@RequestMapping("/parameterStore")
public class ParameterStoreController {

    @Autowired
    private ParameterStoreConfiguration configuration;

    @GetMapping("/ParameterStoreConfiguration")
    public String configuration(){
        return configuration.getHelloWord();
    }

}
