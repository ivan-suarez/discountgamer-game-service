package com.ivansuarez.demo.dto;


public record GameDto (
    String name, 
    String genre, 
    int releaseYear, 
    int score, 
    String developer){
    
}
