package com.rouletteApp.model;

import com.rouletteApp.exception.RouletteException;

public class NumberBet extends Bet {
    private int number;
    public NumberBet(){

    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int number) throws RouletteException {
        if(number<0 || number>36)
            throw new RouletteException(RouletteException.invalidNumber);
        this.number = number;
    }
}
