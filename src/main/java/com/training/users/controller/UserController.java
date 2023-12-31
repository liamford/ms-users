package com.training.users.controller;

import com.training.users.config.VaultConfig;
import com.training.users.model.UserRedisHash;
import com.training.users.repository.UserRepository;
import com.training.users.swagger.model.UserRequest;
import com.training.users.swagger.model.UserResponse;
import com.training.users.swagger.model.Users;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("v1/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final VaultConfig vaultConfig;

    @GetMapping()
    public List<Users> getUsers(@RequestParam(required = false) String userName) {
        log.info("User get API called for Getting users");
        log.info("API KEY = {}", vaultConfig.getApiKey());
        if(userName != null){
            return getUsers(userRepository.findAllByUserName(userName));
        }
        return getUsers(userRepository.findAll());
    }

    @PostMapping()
    public UserResponse addUser(@RequestBody UserRequest userRequest) {
        log.info("User add API called for Adding users {}", userRequest);
        userRepository.save(
                UserRedisHash.builder()
                        .userId(userRequest.getUserID())
                        .userName(userRequest.getUserName())
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .status("Active")
                        .build()
        );

        log.info("UserID:{}", userRequest.getUserID());
        UserResponse userResponse = new UserResponse();
        userResponse.setUserID(userRequest.getUserID());
        userResponse.setCreatedAt(System.currentTimeMillis() + "");
        userResponse.setStatus(UserResponse.StatusEnum.ACTIVE);
        return userResponse;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        log.info("User delete API called for Deleting users {}", userId);
        userRepository.deleteById(userId);
        log.info("User deleted successfully");

    }



    private List<Users> getUsers(Iterable<UserRedisHash> users) {
        return StreamSupport.stream(users.spliterator(), false)
                .map(user -> {
                    Users usr = new Users();
                    usr.setUserID(user.getUserId());
                    usr.setEmail(user.getEmail());
                    usr.setUserName(user.getUserName());
                    if (user.getStatus() != null && user.getStatus().equals("Active"))
                        usr.setStatus(Users.StatusEnum.ACTIVE);
                    else
                        usr.setStatus(Users.StatusEnum.INACTIVE);

                    return usr;
                })
                .toList();
    }

}
