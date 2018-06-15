package com.altale.util;

import java.math.BigInteger;
import java.util.Date;

public class CheckInputUtil {
    public static boolean checkString(String ID){
        BigInteger bi;
        try{
            bi=new BigInteger(ID);
        }catch (Exception ex){
            return false;
        }
        if(bi.compareTo(BigInteger.ZERO)==-1||bi.compareTo(BigInteger.ZERO)==0){
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkTime(String requestTime){
        try{
            DateUtil.strToDate(requestTime);
        }catch (Exception ex){
            return false;
        }
        return true;
    }
    public static boolean checkInput(String requestID, String userID, double amount,String requestTime){
        if(!checkString(requestID)||!checkString(userID)||amount<=0||!checkTime(requestTime)){
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkInput(String requestID, String userID, String merchantID, double amount,String requestTime){
        if(!checkString(requestID)||!checkString(userID)||!checkString(merchantID)||amount<=0||!checkTime(requestTime)){
            return false;
        }else{
            return true;
        }
    }

    /*
    public static void main(String[] args){
        System.out.println(checkString("001"));
        System.out.println(checkString("1abs"));
    }
    */
}
