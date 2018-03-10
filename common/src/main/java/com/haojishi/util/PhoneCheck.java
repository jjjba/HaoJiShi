package com.haojishi.util;

import java.util.regex.Pattern;

/**
 *
 * @author 梁闯
 * @date 2018/03/10 13.51
 *
 */
public class PhoneCheck {

    /**
     * 手机号校验 查取手机前三位手机号是否是正确手机号
     *
     * @param cellphone
     * @return boolean
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,3,6,7,8])|(19[7,9]))\\d{8}$";
        return Pattern.matches(regex,cellphone);

    }

//    public static void main(String[] args) {
//        System.out.println(checkCellphone("17331153729"));
//    }
}

