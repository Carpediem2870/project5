package com.green.borrow.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.green.borrow.common.ResVo;
import com.green.borrow.message.model.*;
import com.green.borrow.user.model.UserEntity;
import com.green.borrow.user.UserMapper;
import com.green.borrow.user.model.UserSelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMapper mapper;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;


    public List<ChatSelVo> getChatAll(ChatSelDto dto) {
        return mapper.selChatAll(dto);
    }


    public ResVo postChatMsg (ChatMsgInsDto dto) {

        int affectedRows = mapper.insChatMsg(dto);
        if (affectedRows == 1){
            int updAffectedRows = mapper.updChatLastMsg(dto);
        }

        LocalDateTime now = LocalDateTime.now(); // 현재 날짜 구하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포멧 정의
        String createdAt = now.format(formatter); // 포멧 적용

        //상대방의 firebaseToken값 필요. 나의 pic, iuser값 필요
        UserEntity otherPerson = mapper.selOtherPersonByLoginUser(dto);


        try {
            if (otherPerson.getFirebaseToken() != null){
                ChatMsgPushVo pushVo = new ChatMsgPushVo();
                pushVo.setIchat(dto.getIchat());
                pushVo.setSeq(dto.getSeq());
                pushVo.setWriterIuser(dto.getLoginedIuser());
                pushVo.setWriterPic(dto.getLoginedPic());
                pushVo.setMsg(dto.getMsg());
                pushVo.setCreatedAt(createdAt);

                String body = objectMapper.writeValueAsString(pushVo);
                log.info("body: {}", body);

                Notification noti = Notification.builder()
                        .setTitle("dm") // 제모각성, 프론트에서 쓰는 분기용
                        .setBody(body) // 내용
                        .build();

                Message message = Message.builder()
                        .setToken(otherPerson.getFirebaseToken())
                        .setNotification(noti)
                        .build();

                FirebaseMessaging.getInstance().sendAsync(message);
                // Async 비동기 - 내가 움직이는것과 상관없이 상대방도 움직일 수 있음
                // 동기 - 턴게임(장기, 체스) 내가 움직이면 상대방이 못움직임
                // 스레드 - 동작단위 (게임 예 : 총게임에 캐릭터 한명한명)
                // 메인스레드는 통신하지 말것

                /*FirebaseMessaging fm = FirebaseMessaging.getInstance(); // 싱글톤
                fm.sendAsync(message);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(dto.getSeq());
    }

    public List<ChatMsgSelVo> getMsgAll(ChatMsgSelDto dto){
        return mapper.selChatMsgAll(dto);
    }

    public ResVo chatDelMsg(ChatMsgDelDto dto){
        int delAffectedRows = mapper.chatDelMsg(dto);
        if (delAffectedRows==1) {
            int updAffectedRows = mapper.updChatLastMsgAfterDelByLastMsg(dto);
        }
        return new ResVo(delAffectedRows);
    }

    public ChatSelVo postChat(ChatInsDto dto){
        Integer isExixtChat = mapper.selChatUserCheck(dto);
        log.info("dto: {}", dto);
        if (isExixtChat==null){
            return null;
        }

        mapper.insChat(dto);
        ChatUserInsDto insDto = new ChatUserInsDto();
        insDto.setIchat(dto.getIchat());
        insDto.setIuser(dto.getOtherPersonIuser());
        mapper.insChatUser(insDto);

        UserSelDto usDto = new UserSelDto();
        usDto.setIuser(dto.getOtherPersonIuser());

        UserEntity entity = userMapper.selUser(usDto);

        ChatSelVo vo = new ChatSelVo();
        vo.setIchat(dto.getIchat());
        vo.setOtherPersonIuser(entity.getIuser());
        vo.setOtherPersonNm(entity.getNm());
        vo.setOtherPersonPic(entity.getPic());
        return vo;
    }

}
