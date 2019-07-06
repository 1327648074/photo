package com.ipa.demo.controller;

import com.ipa.demo.service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/front")
public class AnalyzeController {

    @Autowired
    AnalyzeService analyzeService;

    @PostMapping("/doAnalyze")
    public String doAnalyze (@RequestBody Map<String,String>map){
        return analyzeService.analyze(map.get("path"));
    }
}
