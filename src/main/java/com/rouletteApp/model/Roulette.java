package com.rouletteApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;
import java.util.List;

@RedisHash
public class Roulette implements Serializable {
    @Id
    private String id;
    private boolean open;
    private int winNumber;
    private BetList bets = new BetList();
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
    public void setBets(BetList bets) {
        this.bets = bets;
    }
    public void addBet(Bet bet) {
        bets.add(bet);
    }
    public int getWinNumber() {
        return winNumber;
    }
    public void setWinNumber(int winNumber) {
        this.winNumber = winNumber;
    }
}
