package com.newcoder.wenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET )
    @ResponseBody
    public String index() {
        return "hello world!It is a index-page";
    }

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ResponseBody
    public String session(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        StringBuilder sb = new StringBuilder();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            sb.append(name+"= "+ session.getAttribute(name)+" </br>");
        }
        sb.append("ContextPath="+request.getContextPath()+" </br>");
        sb.append("cookies="+request.getCookies().toString()+" </br>");
        sb.append("PathInfo="+request.getPathInfo()+" </br>");
        sb.append("RequestURI="+request.getRequestURI()+" </br>");
        sb.append("QueryString="+request.getQueryString()+" </br>");
        return sb.toString();
    }


}
