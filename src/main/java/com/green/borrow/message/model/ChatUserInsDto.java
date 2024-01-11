package com.green.borrow.message.model;

import lombok.Data;

@Data
public class ChatUserInsDto {
    private int ichat; // 방 PK
    private int iuser; // 유저 PK
}
