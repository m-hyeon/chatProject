package com.chat.webSocketStompChat.controller;

import com.chat.webSocketStompChat.vo.Greeting;
import com.chat.webSocketStompChat.vo.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello") // 목적지가 path와 일치하는 메시지 수신 시 메서드 호출
    @SendTo("/topic/greetings1") // 구독 채널에 메시지 전달
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
