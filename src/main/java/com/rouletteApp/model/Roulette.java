package com.rouletteApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash
public class Roulette {
    @Id
    private String id;
    private boolean open;
    private List<Bet> bets = new ArrayList<>();

    public  Roulette(){
        open=false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public void addBet(Bet bet) {
        bets.add(bet);
    }
}
