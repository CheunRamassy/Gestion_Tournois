package com.dreams.gestiontournois.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class RoundChampionnat extends Tournois implements Serializable {

    private boolean matchRetour;

    public RoundChampionnat() {}

    public boolean isMatchRetour() {
        return matchRetour;
    }

    public void setMatchRetour(boolean matchRetour) {
        this.matchRetour = matchRetour;
    }
}
