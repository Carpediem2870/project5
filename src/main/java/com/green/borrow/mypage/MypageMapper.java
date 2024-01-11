package com.green.borrow.mypage;

import com.green.borrow.mypage.model.PaymentSelDto;
import com.green.borrow.mypage.model.PaymentSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypageMapper {

    List<PaymentSelVo> getPaymentList(PaymentSelDto dto);
}
