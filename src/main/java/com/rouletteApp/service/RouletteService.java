package com.rouletteApp.service;

import com.rouletteApp.exception.RouletteException;
import com.rouletteApp.model.Bet;
import com.rouletteApp.model.Roulette;

import java.util.List;

public interface RouletteService {
    String addRoulette(Roulette roulette);

    Roulette getRouletteById(String id);

    void openRoulette(String id) throws RouletteException;

    List<Roulette> getAllRoulettes();

    List<Bet> closeRoulette(String id) throws RouletteException;

    void addBet(String rouletteId, Bet bet) throws RouletteException;
}
