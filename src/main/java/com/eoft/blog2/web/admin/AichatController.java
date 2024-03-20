package com.eoft.blog2.web.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eoft.blog2.aichat.V4OkHttpClientTest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/admin")
public class AichatController {
    @GetMapping("/aichat")
    public String getAiChat(){

        return "admin/aichat";
    }
    @PostMapping("/aichat")
    public   ResponseEntity<String> postAiChat(@RequestParam("message")String message){
      String taskid= V4OkHttpClientTest.testAsyncInvoke2(message);

        System.out.println(taskid);
        return new ResponseEntity<>(taskid, HttpStatus.OK);
//        V4OkHttpClientTest.testSseInvoke(message);

        // 返回响应给前端（这里根据实际情况返回相应的数据）
//        return ResponseEntity.ok("Message received");

    }

    @GetMapping("/aichatmore")
    public ResponseEntity<String> getAiChatMore(String taskid){
      String message= V4OkHttpClientTest.testQueryResult(taskid);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


//    @GetMapping(value = "/aichat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public ResponseEntity<SseEmitter> streamAichat() {
//        SseEmitter emitter = new SseEmitter();
//
//        // 启动异步任务，根据任务进度发送事件给客户端
//
//        return ResponseEntity.ok(emitter);
//    }

}
