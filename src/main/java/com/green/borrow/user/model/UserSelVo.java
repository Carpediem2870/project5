package com.green.borrow.user.model;

import lombok.Data;

@Data
public class UserSelVo {
    private int iuser; // 로그인 유저 pk
    private int iproduct; // 상품pk
    private int istatus; // 상품 istatus
    private int cancel; // 결제 istatus
    private int ipayment; // 지불pk
    private int ibuyer; // 빌린 pk
//테스트ㅇㅎ
}
