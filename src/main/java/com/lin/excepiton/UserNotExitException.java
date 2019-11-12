package com.lin.excepiton;

public class UserNotExitException extends RuntimeException{
    public UserNotExitException() {
        super("用户不存在！");
    }
}
