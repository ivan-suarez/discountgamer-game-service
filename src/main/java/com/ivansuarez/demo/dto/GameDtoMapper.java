package com.ivansuarez.demo.dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ivansuarez.demo.model.Game;

@Service
public class GameDtoMapper implements Function<Game, GameDto>{

    @Override
    public GameDto apply(Game game) {
        return new GameDto(
            game.getName(),
            game.getGenre(),
            game.getReleaseYear(),
            game.getScore(),
            game.getDeveloper());
    }
    
}
