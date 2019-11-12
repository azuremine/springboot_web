package com.lin.controller;

import com.lin.excepiton.UserNotExitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

//    //浏览器、客户端返回都是json数据格式
//    @ExceptionHandler(UserNotExitException.class)
//    @ResponseBody
//    public Map<String, Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexit");
//        map.put("message",e.getMessage());
//        return map;
//    }
//
    @ExceptionHandler(UserNotExitException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //传入我们自己的状态码 4xx 5xx
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code", "user.notexit");
        map.put("message", e.getMessage());
        request.setAttribute("ext",map);    //将自定义错误消息放到request域中
        //转发到/error
        return "forward:/error";
    }
}
