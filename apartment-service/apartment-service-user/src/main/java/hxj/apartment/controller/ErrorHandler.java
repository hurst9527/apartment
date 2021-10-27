package hxj.apartment.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HXJ
 * @create 2021-10-19 9:55
 */
//@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public Map<String,Object> errorHand(Exception e){
        HashMap<String, Object> errorMap = new HashMap<>();
        errorMap.put("错误原因",e.getCause());
        errorMap.put("错误信息",e.getMessage());

        return errorMap;
    }

}
