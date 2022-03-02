package com.toker.project.merchants.exception;

public class CouponCheckException extends RuntimeException {

    private Integer code;

    private String message;

    public CouponCheckException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}



