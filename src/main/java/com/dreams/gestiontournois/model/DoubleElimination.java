package com.dreams.gestiontournois.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class DoubleElimination extends Tournois implements Serializable {


    private boolean hasConsolationFinal;

    public DoubleElimination() {}

    public boolean isHasConsolationFinal() {
        return hasConsolationFinal;
    }

    public void setHasConsolationFinal(boolean hasConsolationFinal) {
        this.hasConsolationFinal = hasConsolationFinal;
    }
}
