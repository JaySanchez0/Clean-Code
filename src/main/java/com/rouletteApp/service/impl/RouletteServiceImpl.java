package com.rouletteApp.service.impl;

import com.rouletteApp.exception.RouletteException;
import com.rouletteApp.model.Bet;
import com.rouletteApp.model.Roulette;
import com.rouletteApp.repository.RouletteRepository;
import com.rouletteApp.service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RouletteServiceImpl implements RouletteService {
    @Autowired
    private RouletteRepository repo;

    @Override
    public List<Roulette> getAllRoulettes() {
        ArrayList<Roulette> roulettes = new ArrayList<>();
        for(Roulette roulette: repo.findAll())
            roulettes.add(roulette);

        return  roulettes;
    }

    public String addRoulette(Roulette roulette){
        return repo.save(roulette).getId();
    }

    @Override
    public Roulette getRouletteById(String id) {
        return repo.findById(id).get();
    }

    @Override
    public void openRoulette(String id) throws RouletteException {
        Roulette roulette = getRouletteById(id);
        if(roulette==null || roulette.isOpen())
            throw new RouletteException(RouletteException.invalidOperation);
        roulette.setOpen(true);
        repo.save(roulette);
    }

    @Override
    public List<Bet> closeRoulette(String id) throws RouletteException {
        Roulette roulette = getRouletteById(id);
        if(roulette==null || !roulette.isOpen()){
            throw new RouletteException(RouletteException.invalidOperation);
        }
        roulette.setOpen(false);
        Random random = new Random();
        int winNumber = random.nextInt(37);
        for(Bet bet: roulette.getBets()) bet.validIsWin(winNumber);
        return (ArrayList<Bet>) repo.save(roulette).getBets();
    }

    @Override
    public void addBet(String rouletteId, Bet bet) throws RouletteException{
        Roulette roulette = getRouletteById(rouletteId);
        if(roulette==null || !roulette.isOpen()) throw new RouletteException(RouletteException.inavlidRoulette);
        roulette.addBet(bet);
        repo.save(roulette);
    }

}
