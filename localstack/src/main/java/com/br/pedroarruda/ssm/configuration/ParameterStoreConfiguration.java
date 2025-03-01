package com.br.pedroarruda.ssm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ParameterStoreConfiguration {


    private String helloWord;

    public String getHelloWord() {
        return helloWord;
    }

    public void setHelloWord(String helloWord) {
        this.helloWord = helloWord;
    }

    @Bean
    public ParameterStoreConfiguration configuration(){
        return new ParameterStoreConfiguration();
    }

}
