package com.dreams.gestiontournois.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Game {

    @OneToMany
    private Set<Tournois> tournois;

    public Set<Tournois> getTournois() {
        return tournois;
    }

    public void setTournois(Set<Tournois> tournois) {
        this.tournois = tournois;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<GameMode> availableModes;

    public List<GameMode> getavailableModese() {
        return availableModes;
    }

    public void setGameMode(List<GameMode> availableModes) {
        this.availableModes = availableModes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    public enum GameFormat{
        SINGLE_PLAYER,
        MULTIPLAYER,
        COOPERATIVE,
        COMPETITIVE
    }
    @Enumerated
    private GameFormat gameFormat;


    public Game() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GameFormat getGameFormat() {
        return gameFormat;
    }

    public void setGameFormat(GameFormat gameFormat) {
        this.gameFormat = gameFormat;
    }

    public List<GameMode> getAvailableModes() {
        return availableModes;
    }

    public void setAvailableModes(List<GameMode> availableModes) {
        this.availableModes = availableModes;
    }
}
