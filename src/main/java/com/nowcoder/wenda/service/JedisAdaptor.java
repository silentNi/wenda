//package com.nowcoder.wenda.service;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//
///**
// * Created by silent on 2018/7/3.
// */
//@Component
//public class JedisAdaptor implements InitializingBean {
//    private  static  Jedis  jedis ;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        jedis = new Jedis("127.0.0.1",6379);
//    }
//
//    public static void main(String... args){
//        jedis = new Jedis("127.0.0.1",6379);
//        jedis.select(0);
//        jedis.setex("expire",10,"10s");
//        System.out.println(jedis.get("expire"));
//        try {
//            Thread.sleep(1000*10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(jedis.get("expire"));
////        System.out.println(jedis.ping());
//    }
//
//}
