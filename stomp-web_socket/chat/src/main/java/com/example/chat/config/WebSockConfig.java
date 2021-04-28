package com.example.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker //Stomp 사용
public class WebSockConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //메세지 구동 요청의 prefix /sub로 시작 설정
        config.enableSimpleBroker("/sub");

        // 메세지를 발행하는 요청의 prefix는 /pub로 시작 설정
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp websocket의 연결 endpoint는 /ws-stomp로 설정
        // 도메인이 다른 서버에서도 접속 가능하도록 setAllowedOrigin("*")설정
        // 개발서버의 접속 주소 ws://localhost:8080/ws-stomp
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*")
                .withSockJS();
    }
}
