package com.nowcoder.wenda.service;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


@Service
public class SensitiveService implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        try{
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
            InputStreamReader reader =new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineTxt;
            while((lineTxt=bufferedReader.readLine())!=null){
                addWords(lineTxt.trim());
            }


        }catch (Exception e){
            logger.error("读取敏感词文件失败"+e.getMessage());
        }
    }

    //增加关键词
    public void addWords(String textline){
        TrieNode temp = root;
        for(int i=0;i<textline.length();++i){
            Character key =textline.charAt(i);
            // 判断是否有子节点 没有则创建一个 有就往后走 如果是结尾则标记
            TrieNode node = temp.getSubNodes(key);
            if(node==null){
                node = new TrieNode();
                temp.addSubNodes(key,node);
            }
            temp=node;
            if(i==(textline.length()-1)){
                node.setEnd(true);
            }
        }
    }
    private class TrieNode{
        private boolean end=false;

        private Map<Character,TrieNode> subNodes= new HashMap<>();

        public void addSubNodes(Character key,TrieNode node){
            subNodes.put(key, node);
        }

        public TrieNode getSubNodes(Character key){
            return subNodes.get(key);
        }
        public void setEnd(boolean end){
            this.end=end;
        }
        public boolean isEnd(){
            return end;
        }

    }

    private  TrieNode root = new TrieNode();

    /***
     * 判断是否是颜文字 如 ▽ 等等
     * @param c
     * @return
     */
    private boolean isSymbol(Character c){
        int ic=(int) c;
        //东亚文字  0x2E80 - 0x9FFF
        return !CharUtils.isAsciiAlphanumeric(c) && (ic < 0x2E80 ||ic>0x9FFF);
    }

    /***
     *  对文本进行敏感词过滤 时间复杂度O(n) 线性
     * @param text
     * @return 返回过滤后的文本
     */
    public String filter(String text){
        if(StringUtils.isBlank(text)){ // 为空直接返回
            return text;
        }

        String replacement="***";//替换词
        TrieNode tempNode =root;
        int begin=0;
        int position=0;
        /***
         * Instances of StringBuilder are not safe for use by multiple threads.
         * If such synchronization is required
         * then it is recommended that StringBuffer be used
         */
        StringBuffer sb= new StringBuffer();// 结果
//        StringBuilder sb= new StringBuilder(); //非多线程安全

        while(position < text.length()){
            Character key =text.charAt(position);
            if(isSymbol(key)){ // 如果是颜文字 就跳过
                if(tempNode==root){
                    sb.append(key);
                    begin++;
                }
                position++;
                continue;
            }

            tempNode=tempNode.getSubNodes(key);
            if(tempNode==null){
                tempNode=root;
                sb.append(text.substring(begin,position+1));
                position++;
                begin=position;
                continue;
            }
            if(tempNode.isEnd()){
                tempNode=root;
                sb.append(replacement);
                position++;
                begin=position;
                continue;
            }
            position++;

        }
        return sb.toString();
    }

    public static  void main(String... args){
        // 敏感词过滤测试
        SensitiveService s = new SensitiveService();
        s.addWords("色情");
        s.addWords("赌博");
        s.addWords("好色");
        System.out.println(s.filter("你▽是好▽色▽色▽情好色情"));
        s.addWords("大众点评");
        s.addWords("如果");
        s.addWords("遇到");
        s.addWords("技术");
        System.out.println(s.filter("如果你觉得我们的高可用仍有提升空间，欢迎来大众点评基础平台研发组；\n" +
                "\n" +
                "如果你想更深入学习高可用的技术细节，欢迎来大众点评基础平台研发组；\n" +
                "\n" +
                "如果你想遇到一群志同道合的技术开发，欢迎来大众点评基础平台研发组。"));

    }
}
