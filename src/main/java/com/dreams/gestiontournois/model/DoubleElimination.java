package com.dreams.gestiontournois.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class DoubleElimination extends Tournois implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private boolean hasConsolationFinal;

    public DoubleElimination() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHasConsolationFinal() {
        return hasConsolationFinal;
    }

    public void setHasConsolationFinal(boolean hasConsolationFinal) {
        this.hasConsolationFinal = hasConsolationFinal;
    }
}
