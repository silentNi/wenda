package com.newcoder.wenda.service;

import com.newcoder.wenda.dao.UserDAO;
import com.newcoder.wenda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    public boolean userJudge(String username, String password) {
        return userDAO.userJudge(username, password) == null ? false : true;
    }


}
