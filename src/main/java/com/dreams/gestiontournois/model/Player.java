package com.dreams.gestiontournois.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Player extends User{

   private Long totalTournoisOrganiser;
   private Long totalTournoisParticiper;
   private Long TournoisGagner;

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
