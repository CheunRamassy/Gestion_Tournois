package com.dreams.gestiontournois.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class RoundChampionnat extends Tournois implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private boolean matchRetour;

    public RoundChampionnat() {}
}
