package com.util;

public enum ResponseCode {
    SUCCESS("1","操作成功"),
    FAILURE("11","操作失败");


    private String resultCode;
    private String resultMsg;
    ResponseCode(String code, String msg) {
        this.resultCode=code;
        this.resultMsg=msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

}
