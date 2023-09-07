package com.ivansuarez.demo.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private int releaseYear;
    private int score;
    private String developer;

	public Game(){}

	public Game(String name, String genre, int releaseYear, int score, String developer){
		this.name = name;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.score = score;
		this.developer = developer;
	}

	public Game(Long id, String name, String genre, int releaseYear, int score, String developer){
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.score = score;
		this.developer = developer;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(developer, genre, id, name, releaseYear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(developer, other.developer) && Objects.equals(genre, other.genre)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && releaseYear == other.releaseYear;
	}

}
