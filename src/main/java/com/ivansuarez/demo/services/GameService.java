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

	
    private final GameRepository gameRepository;
    private final GameDtoMapper gameDtoMapper;

    @Autowired
    public GameService(GameRepository gameRepository, GameDtoMapper gameDtoMapper) {
        this.gameRepository = gameRepository;
        this.gameDtoMapper = gameDtoMapper;
    }

    public List<GameDto> getAllGames(){
        return gameRepository.findAll().stream().map(gameDtoMapper).collect(Collectors.toList());
    }

    public Optional<GameDto> getGameById(Long id){
        return gameRepository.findById(id).stream().map(gameDtoMapper).findAny();
    }

    public GameDto saveGame(GameDtoRequest gameDtoRequest){
        Game game = new Game(
            gameDtoRequest.name(),
            gameDtoRequest.genre(),
            gameDtoRequest.releaseYear(),
            gameDtoRequest.score(),
            gameDtoRequest.developer()
        );
        Game savedGame = gameRepository.save(game);
        return gameDtoMapper.apply(savedGame);
    }

    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }

    public Optional<GameDto> updateGame(Long id, GameDtoRequest gameDtoRequest) {
        Optional<Game> gameToUpdate = gameRepository.findById(id);
        if(gameToUpdate.isEmpty()){
            return Optional.empty();
        }else{
            Game updatedGame = gameToUpdate.get();
            updatedGame.setName(gameDtoRequest.name());
            updatedGame.setGenre(gameDtoRequest.genre());
            updatedGame.setReleaseYear(gameDtoRequest.releaseYear());
            updatedGame.setScore(gameDtoRequest.score());
            updatedGame.setDeveloper(gameDtoRequest.developer());
            gameRepository.save(updatedGame);
            return Optional.of(gameDtoMapper.apply(updatedGame));
        }
    }


}
