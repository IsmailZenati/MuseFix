package entities;

import java.util.Date;



public class abonnement {
 private Integer IdAbonnement , userId , IdPack ;
  private Date dateDeb , dateFin;

public abonnement() {
}
public abonnement(  int  IdAbonnement , int userId , int IdPack ,Date dateDeb, Date dateFin){
 this.IdAbonnement=IdAbonnement;
 this.userId=userId;
 this.IdPack = IdPack ;
 this.dateDeb =dateDeb ;
 this.dateFin = dateFin;
}

    public abonnement( int userId , int IdPack ,Date dateDeb, Date dateFin){
        this.userId = userId;
        this.IdPack = IdPack ;
        this.dateDeb =dateDeb ;
        this.dateFin = dateFin;
    }

    public int getIdAbonnement() {
        return IdAbonnement;
    }

    public void setIdAbonnement(Integer idAbonnement) {
        this.IdAbonnement = idAbonnement;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdPack() {
        return IdPack;
    }

    public void setIdPack(Integer idPack) {
        IdPack = idPack;
    }

    public java.sql.Date getDateDeb() {
        return (java.sql.Date) dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public java.sql.Date getDateFin() {
        return (java.sql.Date) dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


    @Override
    public String toString() {
        return "Abonnement ID: " + this.IdAbonnement + "\n" +
                "User ID: " + this.userId + "\n" +
                "Pack ID: " + this.IdPack + "\n" +
                "Date de début: " + this.dateDeb + "\n" +
                "Date de fin: " + this.dateFin;
    }
    }

