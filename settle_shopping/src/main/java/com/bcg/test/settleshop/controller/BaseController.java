package com.bcg.test.settleshop.controller;

import com.bcg.test.settleshop.bean.vo.ResponseVO;
import com.bcg.test.settleshop.utils.ResponseUtil;

import java.util.Map;

/**
 * @author alexlu
 * @date 2019/6/13
 */
public class BaseController {
    protected ResponseVO getSuccess(){
        return ResponseUtil.getSuccess();
    }

    protected ResponseVO getFromData(Object data){
        ResponseVO responseVO = getSuccess();
        responseVO.setData(data);
        return responseVO;
    }

    protected ResponseVO getFailure(){
        return ResponseUtil.getFailure();
    }

    protected ResponseVO getFailure(String msg){
        return ResponseUtil.getFailure(msg);
    }

    protected ResponseVO getFailure(Integer errorCode, String msg){
        return ResponseUtil.getFailure(errorCode, msg);
    }

    protected ResponseVO getFailureWithMap(Integer errorCode, Map<String, String> errMsgMap){
        return ResponseUtil.getFailure(errorCode, errMsgMap);
    }

    protected ResponseVO getResponse(Object data){
        ResponseVO responseVO =  getSuccess();
        responseVO.setData(data);
        return responseVO;
    }
}

