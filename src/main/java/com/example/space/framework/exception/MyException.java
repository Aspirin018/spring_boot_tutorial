package com.example.space.framework.exception;

/**
 * 自定义异常
 * @author liyu
 * @date 18-7-28
 */
public class MyException extends RuntimeException{

    private MsgEnum msgEnum;

    public MyException(MsgEnum msgEnum){
        super(msgEnum.getMessage());
        this.msgEnum = msgEnum;
    }

    public MsgEnum getMsgEnum() {
        return msgEnum;
    }

    public void setMsgEnum(MsgEnum msgEnum) {
        this.msgEnum = msgEnum;
    }
}
