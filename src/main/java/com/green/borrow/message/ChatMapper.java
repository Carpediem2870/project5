package com.green.borrow.message;

import com.green.borrow.message.model.*;
import com.green.borrow.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    //-------------------------------- t_chat
    int insChat(ChatInsDto dto);
    List<ChatSelVo> selChatAll(ChatSelDto dto);

    //-------------------------------- t_chat_user
    int insChatUser(ChatUserInsDto dto);
    Integer selChatUserCheck(ChatInsDto dto);

    //-------------------------------- t_chat_msg
    int insChatMsg(ChatMsgInsDto dto);
    List<ChatMsgSelVo> selChatMsgAll(ChatMsgSelDto dto);
    int updChatLastMsgAfterDelByLastMsg(ChatMsgDelDto dto);
    int updChatLastMsg(ChatMsgInsDto dto);

    int chatDelMsg(ChatMsgDelDto dto); // 메세지 삭제


    UserEntity selOtherPersonByLoginUser(ChatMsgInsDto dto);
}
