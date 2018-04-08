package com.haojishi.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by SongpoLiu on 2017/5/26.
 */
@Data
@NoArgsConstructor
public class BusinessMessage {

    private boolean success = false;
    private String msg = "";
    private int code = 0;
    private Object data = null;
    private Object dataOne = null;
    private Object dataTwo = null;


    public BusinessMessage(boolean success) {
        this.success = success;
    }

    public static BusinessMessage fail(String msg) {
        BusinessMessage message = new BusinessMessage(false);
        message.setMsg(msg);
        return message;
    }

    public static BusinessMessage fail(String msg, int code) {
        BusinessMessage message = fail(msg);
        message.setCode(code);
        return message;

    }
}
