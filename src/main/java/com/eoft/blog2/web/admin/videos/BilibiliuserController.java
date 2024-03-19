package com.eoft.blog2.web.admin.videos;


import com.eoft.blog2.po.Todoitem;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Controller
@RequestMapping("/admin/media")
public class BilibiliuserController {

    @GetMapping("/bilibiliuser")
    public String getlist ( ) {

        return "/admin/media/bilibiliuser";
    }

    @GetMapping("/bilibiliuserget")
    public ResponseEntity<String> getBilibiliUploaderVideos(@RequestParam("uid") String uid) {
        if (uid == null || uid.isEmpty()) {
            return ResponseEntity.badRequest().body("UID is required.");
        }
        System.out.println(new Date());
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(120)) // 连接超时增加到30秒
                .setReadTimeout(Duration.ofSeconds(120)) // 读取超时增加到30秒
                .build();
        String pythonurl = "http://118.31.237.220:888/api/crawl?uid=";
        try {
//             uid = URLEncoder.encode(uid, StandardCharsets.UTF_8.toString());
            String url = pythonurl + uid;
            String responseBody = restTemplate.getForObject(url, String.class);
            System.out.println(url);

            System.out.println(new Date());
            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/showlocalbilibili/{bv}")
    public String showLocalBilibili(@PathVariable String bv, Model model) {
            if(bv=="0")  model.addAttribute("bv",bv);
            return "admin/media/localbilivideos";
        }

//      下面是  访问api的
private static final String API_URL = "https://api.bilibili.com/x/player/pagelist";
@GetMapping("/bilibili/video")
public ResponseEntity<String> getBilibiliVideo(@RequestParam("bvid") String bvid) {

    if(bvid.equals("0")) {
        return null;
    }

    RestTemplate restTemplate = new RestTemplate();
    String url = API_URL + "?bvid=" + bvid;

    try {
        String responseBody = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok().body(responseBody);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
    }
}




}
