package com.example.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket //웹소켓 활성화.
public class WebSockConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //endpoint는 /ws/chat으로 설정 다른 서버에서도 접속 하능하도록 setAllowedOrigin
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
        // 웹소켓에 접속하기 위한 endpoint는 /ws/chat으로 설정
        // 도메인이 다른 서버에서도 접속 가능하도록 setAllowedOrigin("*")설정
        // ws://localhost:8080/ws/chat으로 커넥션을 연결하고 메세지 통신 준비
    }
}
