package com.ivansuarez.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivansuarez.demo.model.Game;
import com.ivansuarez.demo.services.GameService;

@RestController
public class GameController{

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/game/getAll")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    @GetMapping(value = "/game/get/{id}")
    public ResponseEntity<Game> getGameByID(@PathVariable Long id){
        return gameService.getGameById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/game/post")
    public void createGame(@RequestBody Game game){
        gameService.saveGame(game);
    }

    @PutMapping(value = "/game/put/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game){
        return gameService.getGameById(id)
            .map(g -> {
                game.setId(id);
                return ResponseEntity.ok(gameService.saveGame(g));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
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