package com.training.users;

import com.training.users.controller.UserController;
import com.training.users.swagger.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MsUsersApplicationTests {

    @Test
    void test_getUsers_returnsListOfUsers() {
        UserController userController = new UserController();
        List<Users> usersList = userController.getUsers();
        assertNotNull(usersList);
        assertEquals(2, usersList.size());
        assertInstanceOf(Users.class, usersList.get(0));
        assertInstanceOf(Users.class, usersList.get(1));
    }

    @Test
     void test_nonNullUserID() {
        UserController userController = new UserController();
        List<Users> usersList = userController.getUsers();
        assertNotNull(usersList);
        for (Users user : usersList) {
            assertNotNull(user.getUserID());
        }
    }



}
