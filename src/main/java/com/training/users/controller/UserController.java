package com.training.users.controller;

import com.training.users.swagger.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/users")
@Slf4j
public class UserController {

    @GetMapping()
    public List<Users> getUsers() {
        log.info("User get API called for Getting users");

        return List.of(
                mockUser(UUID.randomUUID().toString()),
                mockUser(UUID.randomUUID().toString())
        );
    }

    private Users mockUser(String id) {
        Users user = new Users();
        user.setUserID(id);
        user.setUserName("User " + id);
        user.setEmail("email@" + id + ".com");
        user.setStatus(Users.StatusEnum.ACTIVE);
        return user;
    }

}
