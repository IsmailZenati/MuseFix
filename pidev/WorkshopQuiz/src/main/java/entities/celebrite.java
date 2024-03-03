package entities;
import java.util.Date;

public class celebrite {
    private int idCelebrite;
    private String nom , nationalite , profession;
    private Date dateNais;
    public celebrite() {
    }

    public celebrite(int idCelebrite , String nom ,Date dateNais , String nationalite, String profession) {
        this.idCelebrite = idCelebrite;
        this.nom = nom;
        this.dateNais = dateNais;
        this.nationalite = nationalite;
        this.profession = profession;
    }

    public int getidCelebrite() {
        return idCelebrite;
    }

    public void setidCelebrite(int idCelebrite) {
        this.idCelebrite = idCelebrite;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String titre) {
        this.nom = nom;
    }

    public Date getdateNais() {return dateNais;}
    public void setdateNais(Date dateNais){this.dateNais = dateNais;}

    public String getnationalite() {
        return nationalite;
    }

    public void setnationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getprofession() { return profession; }

    public void setprofession(String profession) {this.profession = profession;}

    @Override
    public String toString() {
        return "quiz{" +
                "idCelebrite=" + idCelebrite + '\'' +
                ", nom=" + nom + '\'' +
                ", dateNais='" + dateNais + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
