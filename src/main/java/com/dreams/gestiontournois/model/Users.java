    package com.dreams.gestiontournois.model;

    import jakarta.persistence.*;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.util.Date;
    import java.util.List;
    import java.util.Set;

    @Entity
    @Table(name = "users")
    public class Users {

        @ManyToOne
        @JoinColumn(name = "id_game")
        private Game jeuPrefere;

        public Game getJeuPrefere() {
            return jeuPrefere;
        }

        public void setJeuPrefere(Game jeuPrefere) {
            this.jeuPrefere = jeuPrefere;
        }

        @OneToMany(mappedBy = "users")
        private Set<Tournois> tournoi;

        public Set<Tournois> getTournoi() {
            return tournoi;
        }

        public void setTournoi(Set<Tournois> tournoi) {
            this.tournoi = tournoi;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(unique = true, nullable = false)
        private String nomUtilisateur;
        private String nomComplet;
        private String password;
        private String email;
        private String pays;
        private String role;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Temporal(TemporalType.TIMESTAMP)
        private Date dateCreation = new Date();
        public enum Niveau{
            DEBUTANT,
            INTERMEDIAIRE,
            EXPERT
        }
        @Enumerated
        private Niveau niveau;
        private Long totalTournoisOrganiser;
        private Long totalTournoisParticiper;
        private Long TournoisGagner;

        public Date getDateCreation() {
            return dateCreation;
        }

        public void setDateCreation(Date dateCreation) {
            this.dateCreation = dateCreation;
        }

        public Niveau getNiveau() {
            return niveau;
        }

        public void setNiveau(Niveau niveau) {
            this.niveau = niveau;
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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getPays() {
            return pays;
        }

        public void setPays(String pays) {
            this.pays = pays;
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
    }
