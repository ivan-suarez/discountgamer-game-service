package com.ivansuarez.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController{

    @GetMapping(value = "/game/getAll")
    public void getAllGames(){
        System.out.println("These are all the videogames");
    }

    @GetMapping(value = "/game/get")
    public void getGame(){
        System.out.println("This is a videogame");
    }

    @PostMapping(value = "/game/post")
    public void createGame(){
        System.out.println("Videogame created");
    }

    @PutMapping(value = "/game/put")
    public void updateGame(){
        System.out.println("Videogame updated");
    }
    @DeleteMapping(value = "/game/delete")
    public void deleteGame(){
        System.out.println("Videogame deleted");
    }
}