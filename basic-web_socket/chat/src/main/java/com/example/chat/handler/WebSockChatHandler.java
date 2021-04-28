package com.example.chat.handler;

import com.example.chat.model.ChatMessage;
import com.example.chat.model.ChatRoom;
import com.example.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
//        TextMessage textMessage = new TextMessage("Welcome chatting server~^^");
//        session.sendMessage(textMessage); //클라이언트로 환영 메세지 보냄.

        //웹소켓 클라이언트로부터 채팅메세지를 전달받아 채팅 메세지 객체로 전환.
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        //전달받은 메세지에 담긴 채팅방 Id로 발송 대상 채팅방 정보를 조호함
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        //해당 채팅방에 입장해있는 모든 클라이언트들(웹 세션)에게 타입에 따른 메세지 발송
        room.handleActions(session, chatMessage, chatService);
    }
}
