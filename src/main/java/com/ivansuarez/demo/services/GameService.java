package com.ivansuarez.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivansuarez.demo.dto.GameDto;
import com.ivansuarez.demo.dto.GameDtoMapper;
import com.ivansuarez.demo.dto.GameDtoRequest;
import com.ivansuarez.demo.model.Game;
import com.ivansuarez.demo.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

    @Autowired
    private GameDtoMapper gameDtoMapper;

    public List<GameDto> getAllGames(){
        return gameRepository.findAll().stream().map(gameDtoMapper).collect(Collectors.toList());
    }

    public Optional<GameDto> getGameById(Long id){
        return gameRepository.findById(id).stream().map(gameDtoMapper).findAny();
    }

    public Game saveGame(GameDtoRequest game){
        return gameRepository.save(new Game(
            game.name(),
            game.genre(),
            game.releaseYear(),
            game.score(),
            game.developer()
        ));
    }

    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }

    public Game updateGame(Long id, GameDtoRequest game) {
        Optional<Game> gameToUpdate = gameRepository.findById(id);
        if(gameToUpdate.isEmpty()){
            return null;
        }else{
            return gameRepository.save(new Game(
                id, 
                game.name(), 
                game.genre(), 
                game.releaseYear(), 
                game.score(), 
                game.developer()
                ));
        }
    }


}
