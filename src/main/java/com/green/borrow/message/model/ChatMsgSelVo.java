package com.green.borrow.message.model;

import lombok.Data;

@Data
public class ChatMsgSelVo {
    private int seq; // 해당 채팅방 메세지 PK
    private int writerIuser; // 작성자 PK
    private String writerPic; // 메세지 보낸 사람 사진
    private String msg; // 메세지
    private String createdAt; // 각 seq에 대한 메세지 보낸 시간
}
