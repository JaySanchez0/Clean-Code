package com.rouletteApp.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rouletteApp.exception.RouletteException;
@JsonTypeName("Color")
public class ColorBet extends Bet{
    private String color;

    public ColorBet(){

    }
    @Override
    public void validIsWin(int number) {
        try {
            if (number % 2 == 0 && color.equals("red"))
                setWinMoney(getWinMoney()+(double) getMoney() * 1.8);
            else if (number % 2 == 1 && color.equals("black"))
                setWinMoney(getWinMoney()+(double) getMoney() * 1.8);
            setMoney(0);
        }catch (Exception e){

        }
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) throws RouletteException {
        if(!color.equals("black") && !color.equals("red")) throw new RouletteException(RouletteException.invalidColor);
        this.color = color;
    }
}
