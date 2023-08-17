package com.ivansuarez.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ivansuarez.demo.dto.GameDto;
import com.ivansuarez.demo.dto.GameDtoMapper;
import com.ivansuarez.demo.model.Game;
import com.ivansuarez.demo.repositories.GameRepository;
import com.ivansuarez.demo.services.GameService;

public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock 
    private GameRepository gameRepository;

    private GameDtoMapper gameDtoMapper;

    @BeforeEach
    public void setUp() {
        gameRepository = Mockito.mock(GameRepository.class);
        gameDtoMapper = new GameDtoMapper();
        gameService = new GameService(gameRepository, gameDtoMapper);
    }


    @Test
    public void getAllGamesTest(){
        Game game1 = new Game(
            1L, "Nier Automata", "RPG", 2017, 5, "Square Enix");
        Game game2 = new Game(
            2L, "Final Fantasy XV", "RPG", 2016, 5, "Square Enix");
        List<Game> gameList = List.of(game1, game2);

        GameDto gameDto1 = new GameDto(
        "Nier Automata", "RPG", 2017, 5, "Square Enix");
        GameDto gameDto2 = new GameDto(
             "Final Fantasy XV", "RPG", 2016, 5, "Square Enix");
        List<GameDto> expectedGameListDto = List.of(gameDto1, gameDto2);

        Mockito.when(gameRepository.findAll()).thenReturn(gameList);
        List<GameDto> actualGameDtoList = gameService.getAllGames();

        assertEquals(expectedGameListDto, actualGameDtoList);
        
    }

    @Test
    public void getGameByIdTest(){
        Game game1 = new Game(
            1L, "Nier Automata", "RPG", 2017, 5, "Square Enix");
       
        GameDto gameDto1 = new GameDto(
                "Nier Automata", "RPG", 2017, 5, "Square Enix");
        Optional<GameDto> expectedGameDto = Optional.of(gameDto1);

        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.of(game1));
        Optional<GameDto> actualGameDto = gameService.getGameById(1L);

        assertEquals(expectedGameDto, actualGameDto);
    }

    public void getGameByIdWhenNotFoundAnyTest(){
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.of(null));
        Optional<GameDto> actualGameDto = gameService.getGameById(1L);

        assertEquals(Optional.of(null), actualGameDto);
    }

    public void saveGameTest(){
        
    }

    public void updateGameTest(){
        
    }

    public void updateGameWhenNotFoundAnyTest(){
        
    }

    public void deleteGameTest(){
        
    }

    public void deleteGameWhenNotFoundAnyTest(){
        
    }

}
