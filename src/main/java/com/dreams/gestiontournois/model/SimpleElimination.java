package com.dreams.gestiontournois.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class SimpleElimination extends Tournois implements Serializable {

    private boolean hasThirdPlaceMatch;

    public boolean isHasThirdPlaceMatch() {
        return hasThirdPlaceMatch;
    }

    public void setHasThirdPlaceMatch(boolean hasThirdPlaceMatch) {
        this.hasThirdPlaceMatch = hasThirdPlaceMatch;
    }

    public SimpleElimination() {

    }


}
