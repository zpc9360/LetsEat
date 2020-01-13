package com.letseat.exception;

import com.letseat.enums.ResultEnum;

/**
 * @author zpc
 * @date 2018/10/8 15:55
 */
public class SellException extends  RuntimeException{
    private Integer statusCode;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.statusCode = resultEnum.getStatusCode();
    }

    public SellException(Integer statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
