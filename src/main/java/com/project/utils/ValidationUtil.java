package com.project.utils;

import java.lang.*;

public class ValidationUtil {
    public static ValidationUtil instanse = new ValidationUtil();
    private String allowablePasswordSymbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public boolean isCorrectNickOrPassword(String password, int minPasswordlength, int maxPasswordlength){
        if(password == null || password.equals("")){
            return false;
        }
        if( (password.length() > maxPasswordlength) || (password.length() < minPasswordlength) ){
            return false;
        }
        char[] temp = password.toCharArray();
        for(int i = 0; i < temp.length; i++){
            boolean charIsCorrect = false;
            for(int j = 0; j < allowablePasswordSymbols.length(); j++){
                if(temp[i] == allowablePasswordSymbols.charAt(j)){
                    charIsCorrect = true;
                    break;
                }
            }
            if(!charIsCorrect){
                return false;
            }
        }
        return true;
    }

    public boolean isCorrectAge(String age){
        try{
            int intAge = Integer.valueOf(age);
            if(intAge < 0){
                return false;
            }
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }

}
