package com.rouletteApp.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rouletteApp.exception.RouletteException;
import org.springframework.data.redis.core.RedisHash;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ColorBet.class, name = "Color"),
        @JsonSubTypes.Type(value = NumberBet.class, name = "Number")
})
@RedisHash
public class Bet {
    private int money;
    private String idClient;

    public Bet(){

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) throws RouletteException {
        if(money<0 || money>10000)
            throw new RouletteException(RouletteException.notIsMoney);
        this.money = money;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
}
