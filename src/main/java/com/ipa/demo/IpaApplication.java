package com.ipa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ipa.demo.service.AnalyzeService;

@SpringBootApplication
public class IpaApplication {

    public static void main(String[] args) {
        AnalyzeService analyzeService =new AnalyzeService();
        String s= analyzeService.analyze("F:\\TimFile\\buldings\\4.jpg");
        SpringApplication.run(IpaApplication.class, args);
        System.out.println(s);
    }

}
