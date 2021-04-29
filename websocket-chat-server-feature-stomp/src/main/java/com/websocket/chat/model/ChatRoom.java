package com.websocket.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;

    //pub/sub 방식을 사용하면 구독자 관리가 알아서 되므로 웹소켓 세션관리가 필요없음.
    //발송의 구현도 알아서 해결되므로 일일이 클라이언트에게 메세지를 발송하는 구현이 필요없음.
    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString(); //랜덤 방번호 설정
        chatRoom.name = name;
        return chatRoom;
    }
    // 채팅방 DTO가 간소화 됐음을 확인하기.
}
