package com.bcg.test.settleshop.utils;

import com.bcg.test.settleshop.bean.vo.ResponseVO;
import com.bcg.test.settleshop.enums.SystemErrorCodeEnum;

import java.util.Map;

/**
 * Created by alexlu on 2017/1/9.
 */
public class ResponseUtil {
    public static ResponseVO getSuccess(){
        return new ResponseVO(SystemErrorCodeEnum.SUCCESS.getCode(),"Succeed!");
    }
    public static ResponseVO getFailure(){
        return new ResponseVO(SystemErrorCodeEnum.SYSTEM_ERROR.getCode(),"Failed!");
    }

    public static ResponseVO getFailure(String msg){
        return new ResponseVO(SystemErrorCodeEnum.SYSTEM_ERROR.getCode(),msg);
    }

    public static ResponseVO getFailure(Integer errorCode, String msg){
        return new ResponseVO(errorCode, msg);
    }

    public static ResponseVO getFailure(Integer errorCode, Map<String, String> errMsgMap){
        StringBuilder stb = new StringBuilder();
        for (String errMsg : errMsgMap.values()){
            stb.append(errMsg);
        }
        return new ResponseVO(errorCode, stb.toString());
    }
}
