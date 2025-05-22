package com.dreams.gestiontournois.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "tournois")
public class Tournois {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nom;
    //    private String jeu;
//    private String modeDeJeu;
    private Integer nombreParticipant;
    private Integer nombreEquipes;
    private String formatMatch;
    private String prix;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateInscriptionDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateInscriptionFin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;


//    public String getJeu() {
//        return jeu;
//    }
//
//    public void setJeu(String jeu) {
//        this.jeu = jeu;
//    }

//    public String getModeDeJeu() {
//        return modeDeJeu;
//    }
//
//    public void setModeDeJeu(String modeDeJeu) {
//        this.modeDeJeu = modeDeJeu;
//    }

    public Integer getNombreParticipant() {
        return nombreParticipant;
    }

    public void setNombreParticipant(Integer nombreParticipant) {
        this.nombreParticipant = nombreParticipant;
    }

    public Integer getNombreEquipes() {
        return nombreEquipes;
    }

    public void setNombreEquipes(Integer nombreEquipes) {
        this.nombreEquipes = nombreEquipes;
    }

    public String getFormatMatch() {
        return formatMatch;
    }

    public void setFormatMatch(String formatMatch) {
        this.formatMatch = formatMatch;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateInscriptionDebut() {
        return dateInscriptionDebut;
    }

    public void setDateInscriptionDebut(Date dateInscriptionDebut) {
        this.dateInscriptionDebut = dateInscriptionDebut;
    }

    public Date getDateInscriptionFin() {
        return dateInscriptionFin;
    }

    public void setDateInscriptionFin(Date dateInscriptionFin) {
        this.dateInscriptionFin = dateInscriptionFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


}

