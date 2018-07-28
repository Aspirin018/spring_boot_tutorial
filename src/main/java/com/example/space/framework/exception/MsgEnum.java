package com.example.space.framework.exception;

/**
 * @author liyu
 * @date 18-7-28
 */
public enum MsgEnum {

    /**
     * 系统未知错误
     */
    ERROR(-2, "系统错误"),
    /**
     * json转换错误
     */
    JSON_CONVERT_ERROR(-3, "json转换错误");

    private int code;
    private String message;

    MsgEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
