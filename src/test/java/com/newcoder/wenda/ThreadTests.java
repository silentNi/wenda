package com.newcoder.wenda;

import com.newcoder.wenda.model.ThreadId;

import java.util.Hashtable;

/**
 * Created by silent on 2018/7/5.
 */

public class ThreadTests {
//    @Autowired
//    private ThreadId threadId;


    public  static void main(String... args){
//        ThreadIdtest();
        Threadtest();
    }

    private static void ThreadIdtest() {

        for(int i=0;i<11;++i){
//            ThreadId threadId =new ThreadId();
            System.out.println(ThreadId.get());
        }
    }


    private static void Threadtest() {
//        Hashtable
        for(int i=0;i<11;++i){
            final int  fi=i;
            new Thread(){
                @Override
                public void run() {
                    System.out.println(fi);
                }
            }.start();

        }

    }


}
