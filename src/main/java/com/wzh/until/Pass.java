package com.wzh.until;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pass {
    public static String opinions(String pass) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,12})$");
        Matcher matcher = pattern.matcher(pass);

        if (matcher.matches()) {
            System.out.println("密码符合要求");
            return "200";

        } else {
            System.out.println("密码不符合要求");
            return "500";
        }
    }
}
