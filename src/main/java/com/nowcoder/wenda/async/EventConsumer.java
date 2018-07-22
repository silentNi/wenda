package com.nowcoder.wenda.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nowcoder.wenda.util.JedisAdapter;
import com.nowcoder.wenda.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventConsumer implements InitializingBean,ApplicationContextAware {
    private static final Logger logger =LoggerFactory.getLogger(EventConsumer.class);
    @Autowired
    JedisAdapter jedisAdapter;

    private Map<EventType, List<EventHandler>> config = new HashMap<>();

    private ApplicationContext context; // Spring上下文

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 通过spring自动获取所有实现了EventHandler 的类
        Map<String,EventHandler> beans= context.getBeansOfType(EventHandler.class);
        System.out.println("beans:"+beans);
        if(beans.size()>0){
            for(Map.Entry<String,EventHandler> entry:beans.entrySet()){
                List<EventType> supportTypes= entry.getValue().getSupportTypes();

                for(EventType type:supportTypes){

                    if(!config.containsKey(type)){
                        config.put(type,new ArrayList<>());
                    }
                    config.get(type).add(entry.getValue());
                }

            }
        }

        // DO: 2018/7/22 建立线程不断从redis中读取待处理的事件 判断其所需要通知的EventHandler，让他去完成接下来的处理
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    List<String> message=jedisAdapter.brpop(0,RedisKeyUtil.getEventQueueKey());
                    for(String json:message){
                        if(json.equals(RedisKeyUtil.getEventQueueKey())){
                            continue;
                        }
                        System.out.println("json:"+json);
                        EventModel eventModel = JSON.parseObject(json,EventModel.class);
                        System.out.println("eventModel:"+eventModel);
                        if( !config.containsKey(eventModel.getType()) ){
                            logger.error("不能识别的事件");
                            continue;
                        }
                        List<EventHandler> handlers = config.get(eventModel.getType());
                        System.out.println("handlers:"+handlers);
                        for (EventHandler handler:handlers){
                            handler.doHandler(eventModel);
                        }
                    }
                }
            }
        }
        );
        thread.start();

    }
}
