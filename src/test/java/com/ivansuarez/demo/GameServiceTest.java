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
import com.ivansuarez.demo.dto.GameDtoRequest;
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

    @Test
    public void getGameByIdWhenNotFoundAnyTest(){
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<GameDto> actualGameDto = gameService.getGameById(1L);

        assertEquals(Optional.empty(), actualGameDto);
    }

    @Test
    public void saveGameTest(){
        Game game1 = new Game(
            "Nier Automata", "RPG", 2017, 5, "Square Enix");
        Game game2 = new Game(
            1L, "Nier Automata", "RPG", 2017, 5, "Square Enix");
        Mockito.when(gameRepository.save(game1)).thenReturn(game2);
        GameDto expectedDto = new GameDto(
            "Nier Automata",
            "RPG",
            2017,
            5,
            "Square Enix");
        GameDtoRequest gameDto = new GameDtoRequest(
            "Nier Automata", "RPG", 2017, 5, "Square Enix");
        GameDto actualGame = gameService.saveGame(gameDto);
        assertEquals(expectedDto, actualGame);
    }

    @Test
    public void updateGameTest(){
        Game game1 = new Game(
            1L,
            "Nier Automata", "RPG", 2017, 5, "Square Enix");
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.of(game1));
        GameDtoRequest gameDto = new GameDtoRequest(
            "Nier Automata", "RPG", 2017, 4, "Square Enix");
        Game game2 = new Game(
                1L, "Nier Automata", "RPG", 2017, 4, "Square Enix");
        Game game3 = new Game(
                    1L, "Nier Automata", "RPG", 2017, 4, "Square Enix");
        Mockito.when(gameRepository.save(game2)).thenReturn(game3);
        Optional<GameDto> updatedGame = gameService.updateGame(1L, gameDto);
        GameDto expectedDto = new GameDto(
            "Nier Automata",
            "RPG",
            2017,
            4,
            "Square Enix");
        assertEquals(expectedDto, updatedGame.get());
    }

    @Test
    public void updateGameWhenNotFoundAnyTest(){
        GameDtoRequest gameDto = new GameDtoRequest(
            "Nier Automata", "RPG", 2017, 4, "Square Enix");
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<GameDto> updatedGame = gameService.updateGame(1L, gameDto);
        assertEquals(Optional.empty(), updatedGame);
    }

}
