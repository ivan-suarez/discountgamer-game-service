package com.ivansuarez.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivansuarez.demo.model.Game;
import com.ivansuarez.demo.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

    

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id){
        return gameRepository.findById(id);
    }

    public Game saveGame(Game game){
        return gameRepository.save(game);
    }

    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }


}
