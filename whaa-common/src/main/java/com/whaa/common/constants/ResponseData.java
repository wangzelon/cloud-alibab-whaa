package com.whaa.common.constants;

import lombok.Data;

import java.io.Serializable;

/**
 * created by @author wangzelong 2020/3/23 15:27
 */
@Data
public class ResponseData<T> implements Serializable {
    /**
     * 自定义业务状态码
     */
    private int code;
    /**
     * "消息"
     */
    private String msg;
    /**
     * "数据"
     */
    private T data;

    public ResponseData(String msg, T data, int code) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(String msg, int code) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData() {
        this.code = CodeConstant.SUCCESS;
        this.msg = MessageConstant.SUCCESS;
    }

    public static ResponseData success() {
        ResponseData baseReturn = new ResponseData();
        baseReturn.code = CodeConstant.SUCCESS;
        baseReturn.msg = MessageConstant.SUCCESS;
        return baseReturn;
    }


    public static ResponseData fail() {
        ResponseData baseReturn = new ResponseData();
        baseReturn.code = CodeConstant.FAIL;
        baseReturn.msg = MessageConstant.OPERATION_FAILED;
        return baseReturn;
    }

    public static ResponseData fail(String msg) {
        ResponseData baseReturn = new ResponseData();
        baseReturn.code = CodeConstant.OPERATION_FAILED;
        baseReturn.msg = msg;
        return baseReturn;
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData baseReturn = new ResponseData();
        baseReturn.code = CodeConstant.SUCCESS;
        baseReturn.msg = MessageConstant.SUCCESS;
        baseReturn.data = data;
        return baseReturn;
    }

}
