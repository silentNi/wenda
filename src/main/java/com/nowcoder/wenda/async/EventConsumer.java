package com.nowcoder.wenda.async;

import com.nowcoder.wenda.util.JedisAdapter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EventConsumer implements InitializingBean {
    @Autowired
    JedisAdapter jedisAdapter;
    private HashMap<String, List<Integer>> map = new HashMap<>();
    @Autowired
    ApplicationContext context;


    @Override
    public void afterPropertiesSet() throws Exception {
        // 通过spring自动获取所有实现了EventHandler 的类
        // TODO: 2018/7/20 建立线程不断从redis中读取待处理的事件 判断其所需要通知的EventHandler，让他去完成接下来的处理
//        context.getBeansOfType(EventHandler.class);
    }
}
