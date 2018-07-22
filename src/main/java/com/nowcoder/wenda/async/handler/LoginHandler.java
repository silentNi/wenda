package com.nowcoder.wenda.async.handler;

import com.nowcoder.wenda.async.EventHandler;
import com.nowcoder.wenda.async.EventModel;
import com.nowcoder.wenda.async.EventType;
import com.nowcoder.wenda.model.User;
import com.nowcoder.wenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author silent 1183611386@qq.com
 * @date 2018/7/20 14:40
 */
@Component
public class LoginHandler implements EventHandler {
//    private static final List<EventType> supportTypes = new ArrayList<>();
    @Autowired
    UserService userService;

    @Override
    public void doHandler(EventModel model) {
        User user = userService.getUser(model.getOwnerId());
        System.out.println("user:"+user);
        user.setPassword("LoginHandlerWorks");
        userService.updatePwd(user);
    }

    @Override
    public List<EventType> getSupportTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
