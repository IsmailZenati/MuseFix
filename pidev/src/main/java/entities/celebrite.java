package entities;

import java.util.Date;

public class celebrite {
    private int idCelebrite;
    private String nom;
    private String nationalite;
    private String profession;
    private Date dateNais;

    public celebrite() {
    }

    public celebrite(int idCelebrite, String nom, Date dateNais, String nationalite, String profession) {
        this.idCelebrite = idCelebrite;
        this.nom = nom;
        this.dateNais = dateNais;
        this.nationalite = nationalite;
        this.profession = profession;
    }

    public int getIdCelebrite() {
        return idCelebrite;
    }

    public void setIdCelebrite(int idCelebrite) {
        this.idCelebrite = idCelebrite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNais() {
        return dateNais;
    }

    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "celebrite{" +
                "idCelebrite=" + idCelebrite + '\'' +
                ", nom=" + nom + '\'' +
                ", dateNais='" + dateNais + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
