package com.ivansuarez.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivansuarez.demo.dto.GameDto;
import com.ivansuarez.demo.dto.GameDtoRequest;
import com.ivansuarez.demo.services.GameService;

@RestController
public class GameController{

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/game/getAll")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<GameDto> getAllGames(){
        return gameService.getAllGames();
    }

    @GetMapping(value = "/game/get/{id}")
    public ResponseEntity<GameDto> getGameByID(@PathVariable Long id){
        return gameService.getGameById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/game/post")
    public void createGame(@RequestBody GameDtoRequest game){
        gameService.saveGame(game);
    }

    @PutMapping(value = "/game/put/{id}")
    public ResponseEntity<GameDto> updateGame(@PathVariable Long id, @RequestBody GameDtoRequest game){
        Optional<GameDto> g = gameService.updateGame(id, game);
        return !g.isEmpty() ? ResponseEntity.ok(g.get()) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping(value = "/game/delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        if (gameService.getGameById(id).isPresent()) {
            gameService.deleteGame(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}