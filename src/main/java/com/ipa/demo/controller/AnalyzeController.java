package com.ipa.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.ipa.demo.service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/front")
public class AnalyzeController {

    @Autowired
    AnalyzeService analyzeService;

    @PostMapping("/doAnalyze")
    public Object doAnalyze (@RequestBody Map<String,String>map){
        File file = new File(map.get("path"));
        String name = file.getName();
        JSONObject fileItem = new JSONObject();
        fileItem.put("name", name);
        fileItem.put("folder",  analyzeService.analyze(map.get("path")));
        return fileItem;
    }
}
