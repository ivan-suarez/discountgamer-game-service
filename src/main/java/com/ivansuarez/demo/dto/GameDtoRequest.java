package com.ivansuarez.demo.dto;

public record GameDtoRequest (
    String name, 
    String genre, 
    int releaseYear, 
    int score, 
    String developer){
}
