package com.dreams.gestiontournois.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomUtilisateur;
    private String nomComplet;
    private String password;
    private String email;
    private String Pays;
    private Long totalTournoisOrganiser;
    private Long totalTournoisParticiper;
    private Long TournoisGagner;

    public String getPays() {
        return Pays;
    }

    public void setPays(String pays) {
        Pays = pays;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public Long getTotalTournoisOrganiser() {
        return totalTournoisOrganiser;
    }

    public void setTotalTournoisOrganiser(Long totalTournoisOrganiser) {
        this.totalTournoisOrganiser = totalTournoisOrganiser;
    }

    public Long getTotalTournoisParticiper() {
        return totalTournoisParticiper;
    }

    public void setTotalTournoisParticiper(Long totalTournoisParticiper) {
        this.totalTournoisParticiper = totalTournoisParticiper;
    }

    public Long getTournoisGagner() {
        return TournoisGagner;
    }

    public void setTournoisGagner(Long tournoisGagner) {
        TournoisGagner = tournoisGagner;
    }
}
