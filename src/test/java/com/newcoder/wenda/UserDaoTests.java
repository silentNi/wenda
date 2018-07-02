package com.newcoder.wenda;

import com.newcoder.wenda.dao.UserDAO;
import com.newcoder.wenda.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/init-schema.sql")
public class UserDaoTests {
    @Autowired
    UserDAO userDAO;

    @Test
    public void userDaoTests() {
        Random random = new Random();
        for (int i = 1; i < 11; ++i) {
            List<User> users = userDAO.selectUserById(i);
            System.out.println(users.get(0).toString());
        }
    }

}
