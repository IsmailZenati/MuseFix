package entities;

import java.util.Date;

public class quiz {
    private int idQuiz;
    private String nom;
    private String titre;
    private String description;
    private String difficulte;
    private Date dateCreation;

    public quiz() {}

    public quiz( String titre, String description, String difficulte, Date dateCreation, String nom) {

        this.titre = titre;
        this.description = description;
        this.difficulte = difficulte;
        this.dateCreation = dateCreation;
        this.nom = nom;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "quiz{" +
                "idQuiz=" + idQuiz +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", difficulte='" + difficulte + '\'' +
                ", dateCreation=" + dateCreation +
                ", nom=" + nom +
                '}';
    }
}
