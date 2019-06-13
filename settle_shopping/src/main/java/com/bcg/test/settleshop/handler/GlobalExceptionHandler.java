package com.bcg.test.settleshop.handler;

import com.bcg.test.settleshop.bean.vo.ResponseVO;
import com.bcg.test.settleshop.controller.BaseController;
import com.bcg.test.settleshop.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * Created by alexlu at 2019/06/13.
 * Catch global exception
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO jsonErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception ignore) {
        }

        String postData = sb.toString();
        String path = request.getRequestURI();
        int localPort = request.getLocalPort();

        logger.debug("[Request] IP:{}, Local Port:{}, Url:{}, Post Data:{}", getIpAddr(request), localPort, path, postData);
        e.printStackTrace();

        if(e instanceof BusinessException){
            return getFailure(((BusinessException) e).getCode(), ((BusinessException) e).getMsg());
        }
        return getFailure("服务器异常请稍后再试!");
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
