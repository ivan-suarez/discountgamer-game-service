package com.ivansuarez.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivansuarez.demo.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
    
}