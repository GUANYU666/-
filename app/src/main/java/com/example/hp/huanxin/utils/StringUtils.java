package com.example.hp.huanxin.utils;

import com.hyphenate.util.HanziToPinyin;

public class StringUtils  {
    public static boolean checkUsername(String usetname)
    {
        if(usetname==null) {
            return false;
        }
        return usetname.matches("^[a-zA-Z]\\w{2,19}$");
    }
    public static boolean checkPassword(String Password)
    {
        if(Password==null) {
            return false;
        }
        return Password.matches("^[0-9]{3,20}$");
    }

    public static String getInit(String username)
    {
        if(username == null)
        {


            return "";
        }
        return username.substring(0,1).toUpperCase();
    }
}
