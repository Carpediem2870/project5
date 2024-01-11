package com.green.borrow.user;

import com.green.borrow.common.ResVo;
import com.green.borrow.user.model.UserDelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;


    @PatchMapping
    public ResVo delUser(@RequestBody UserDelDto dto){

        return null;
    }
}
