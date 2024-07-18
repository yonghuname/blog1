package com.eoft.blog2.web.admin;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class ChartController {
    @GetMapping("/chart22")// 先废弃 这是什么啊 占位符 建议删掉
        public String getchart(){
        return "/admin/chart";
    }


    @GetMapping("/chart")
     public ResponseEntity<String>  getchart1() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String htmlContent = restTemplate.getForObject("http://47.121.131.164:889/", String.class);

        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }
}
