package com.newcoder.wenda.async;

import com.newcoder.wenda.util.JedisAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {
    @Autowired
    private JedisAdaptor jedisAdaptor;
    public boolean fireEvent(EventModel eventModel){
        try {
            String json = JSONObject.toJSONString(eventModel);
            String key = "EVENTQUEUE";
//            String key = RedisKeyUtil.getEventQueueKey();
            jedisAdaptor.lpush(key, json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
