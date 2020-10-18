package com.rouletteApp.exception;

public class RouletteException extends Exception{

    public static final String invalidOperation = "Invalid operation";
    public static final String invalidColor = "The color is invalid";
    public static final String invalidNumber = "The number is invalid";
    public static final String notIsMoney = "The number there are a valid money";

    public RouletteException(String menssage){
        super(menssage);
    }

}
