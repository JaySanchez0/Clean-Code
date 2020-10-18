package com.rouletteApp.model;

import com.rouletteApp.exception.RouletteException;

public class ColorBet extends Bet{
    private String color;

    public ColorBet(){

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) throws RouletteException {
        if(!color.equals("black") && !color.equals("red")) throw new RouletteException(RouletteException.invalidColor);
        this.color = color;
    }
}
