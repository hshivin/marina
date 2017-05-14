package com.hivin.Params;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Data;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/16
 */

public enum ReponseCode {
    SUCCESS(200, "success"),
    PARAMERROR(400, "param error"),
    SERVICEERROR(500, "service error");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ReponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ReponseCode selectByCode(int code) {
        for (ReponseCode reponseCode : ReponseCode.values()) {
            if (reponseCode.getCode() == code) {
                return reponseCode;
            }
        }
        return null;
    }


}
