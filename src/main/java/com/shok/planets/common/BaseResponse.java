package com.shok.planets.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wy
 * @version 1.0
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String massage;

    private String description;

    public BaseResponse(int code, T data, String massage, String description) {
        this.code = code;
        this.data = data;
        this.massage = massage;
        this.description = description;
    }

    public BaseResponse(int code, T data, String massage) {
        this(code, data, massage,"");
    }

    public BaseResponse(int code, T data) {
        this(code, data, "","");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(),errorCode.getDescription());
    }
}
