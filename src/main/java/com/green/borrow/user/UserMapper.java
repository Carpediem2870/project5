package com.green.borrow.user;

import com.green.borrow.user.model.UserDelDto;
import com.green.borrow.user.model.UserEntity;
import com.green.borrow.user.model.UserSelDto;
import com.green.borrow.user.model.UserSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserEntity selUser(UserSelDto dto);

    //유저 삭제
    List<UserSelVo> selBeforDelUser(int iuser);
    int delUserByPics(int iproduct);
    Integer delUserByPaymentHiding(int iuser);
    int delUserByProduct(int iuser);
    int delUser(UserDelDto dto);
}
