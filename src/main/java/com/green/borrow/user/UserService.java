package com.green.borrow.user;

import com.green.borrow.common.ResVo;
import com.green.borrow.user.model.UserDelDto;
import com.green.borrow.user.model.UserSelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;

    public ResVo delUser(UserDelDto dto){

        List<UserSelVo> vo = mapper.selBeforDelUser(dto.getIuser());

        for (UserSelVo selVo : vo) {
            mapper.delUserByProduct(selVo.getIuser());
            mapper.delUserByPics(selVo.getIproduct());
            mapper.delUserByPaymentHiding(selVo.getIuser());
        }


        return null;
    }
}
