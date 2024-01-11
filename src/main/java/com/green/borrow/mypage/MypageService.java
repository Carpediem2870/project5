package com.green.borrow.mypage;

import com.green.borrow.mypage.model.PaymentSelDto;
import com.green.borrow.mypage.model.PaymentSelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageMapper mapper;

    public List<PaymentSelVo> paymentList(PaymentSelDto dto){

        return mapper.getPaymentList(dto);
    }
}
