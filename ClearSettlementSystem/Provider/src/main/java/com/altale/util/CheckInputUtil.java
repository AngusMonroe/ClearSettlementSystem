package com.altale.util;

import java.math.BigInteger;

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
    public static boolean checkInput(String requestID, String userID, double amount){
        if(!checkString(requestID)){
            return false;
        }else if(!checkString(userID)){
            return false;
        }else if(amount<=0){
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkInput(String requestID, String userID, String merchantID, double amount){
        if(!checkString(requestID)){
            return false;
        }else if(!checkString(userID)){
            return false;
        }else if(!checkString(merchantID)){
            return false;
        }else if(amount<=0){
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
