package com.letseat.vo;

import lombok.Data;

/**
 * 请求返回的最外层对象
 *
 * @author zpc
 * @date 2018/9/27 20:21
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体数据
     */
    private T data;


}
