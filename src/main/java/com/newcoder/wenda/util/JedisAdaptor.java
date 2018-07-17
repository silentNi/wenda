package com.newcoder.wenda.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/7/2.
 */
@Component
public class JedisAdaptor implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(JedisAdaptor.class);
    private JedisPool pool;
    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/10");
    }


    public long lpush(String key ,String value){
        Jedis jedis=null;
        try{
            jedis=pool.getSource();
            return jedis.lpush(key,value);
        }catch (Exception e){
            logger.error("jedis错误："+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return 0;
    }
    public List<String> brpop(int  timeout,String key ){
            Jedis jedis=null;
            try{
                jedis=pool.getSource();
                return jedis.brpop(timeout,key);
            }catch (Exception e){
                logger.error("jedis错误："+e.getMessage());
            }finally {
                if(jedis!=null){
                    jedis.close();
                }
            }
            return null;
        }
}
