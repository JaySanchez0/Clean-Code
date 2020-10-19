package com.rouletteApp.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rouletteApp.exception.RouletteException;
@JsonTypeName("Number")
public class NumberBet extends Bet {

    private int number;

    public NumberBet(){
    }

    public NumberBet(int money, int number) throws RouletteException {
        this.number = number;
        setMoney(money);
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number) throws RouletteException {
        if(number<0 || number>36)
            throw new RouletteException(RouletteException.invalidNumber);
        this.number = number;
    }

    @Override
    public void validIsWin(int winNumber) {
        if(number==winNumber) setWinMoney(getWinMoney()+5*getMoney());
        try {
            setMoney(0);
        }catch (Exception e){

        }
    }
}
