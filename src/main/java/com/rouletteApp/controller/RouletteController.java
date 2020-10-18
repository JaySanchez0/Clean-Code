package com.rouletteApp.controller;

import com.rouletteApp.exception.RouletteException;
import com.rouletteApp.model.Bet;
import com.rouletteApp.model.Roulette;
import com.rouletteApp.service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/roulette")
public class RouletteController {

    @Autowired
    private RouletteService service;

    @GetMapping
    public ResponseEntity<?> getAllRoulettes(){
        return ResponseEntity.ok(service.getAllRoulettes());
    }

    @PostMapping
    public ResponseEntity<?> createRoulette(){
        Roulette roulette = new Roulette();
        return ResponseEntity.ok(service.addRoulette(roulette));
    }

    @PatchMapping(value="/{id}/open")
    public ResponseEntity<?> openRoulette(@PathVariable String id){
        try {
            service.openRoulette(id);
            return ResponseEntity.accepted().build();
        } catch (RouletteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(value="/{id}/close")
    public ResponseEntity<?> closeRoulette(@PathVariable String id){
        try {
            return ResponseEntity.ok(service.closeRoulette(id));
        } catch (RouletteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/{rouletteId}/bets")
    public ResponseEntity<?> addBeat(@RequestHeader(value = "Authorization") String auth,@PathVariable String rouletteId, @RequestBody Bet bet){
        try {
            service.addBet(rouletteId,bet);
            return ResponseEntity.accepted().build();
        } catch (RouletteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
