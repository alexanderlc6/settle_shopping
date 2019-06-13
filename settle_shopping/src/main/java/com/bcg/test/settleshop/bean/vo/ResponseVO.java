package com.bcg.test.settleshop.bean.vo;


import java.io.Serializable;

/**
 *
 * Restful common vo
 * Created by alexlu on 2019/6/13
 */
public class ResponseVO implements Serializable{
    /**
     * Success flag
     */
    private boolean success;


    /**
     * Api return message
     */
    private String message;

    /**
     * Api return code
     * 20000-Succeed
     */
    private int code;


    /**
     * Api return object
     */
    private Object data;

    public ResponseVO(){}


    public ResponseVO(int code, String message){
        this.message = message;
        this.code = code;
        if(code>=20000 && code < 30001){
            this.success = true;
        }
    };

    public ResponseVO(boolean success, String message, int code){
        this.message = message;
        this.code = code;
        this.success = success;
    };

    public ResponseVO(String message, int code, Object data){
        this(code,message);
        this.data = data;
    };

    public ResponseVO(boolean success, String message, int code, Object data){
        this(success,message,code);
        this.data = data;
    };
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
