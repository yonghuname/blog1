package com.eoft.blog2.web.admin.videos;


import com.eoft.blog2.po.Todoitem;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/admin/media")
public class BilibiliuserController {

    @GetMapping("/bilibiliuser")
    public String getlist ( ) {

        return "/admin/media/bilibiliuser";
    }

    @PostMapping("/bilibiliuser")
    public String postlist ( ) {

        return "/admin/media/bilibiliuser";
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
