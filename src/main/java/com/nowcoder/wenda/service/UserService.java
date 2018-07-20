package com.nowcoder.wenda.service;

import com.nowcoder.wenda.dao.UserDAO;
import com.nowcoder.wenda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    public int updatePwd(User user) {
        return userDAO.updatePassword(user.getId(), user.getPassword());
    }
    public User getUser(int id) {
        return userDAO.selectUserById(id);
    }

    public boolean userJudge(String username, String password) {
        return userDAO.userJudge(username, password) != null;
    }


}
