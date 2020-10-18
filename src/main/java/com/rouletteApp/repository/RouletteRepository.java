package com.rouletteApp.repository;

import com.rouletteApp.model.Roulette;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouletteRepository extends CrudRepository<Roulette,String> {

}
