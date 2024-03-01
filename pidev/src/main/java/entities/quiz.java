package entities;

import java.util.Date;

public class quiz {
    private int idQuiz;
    private int idCelebrite;
    private String titre;
    private String description;
    private String difficulte;
    private Date dateCreation;

    public quiz() {}

    public quiz(int idQuiz, String titre, String description, String difficulte, Date dateCreation, int idCelebrite) {
        this.idQuiz = idQuiz;
        this.titre = titre;
        this.description = description;
        this.difficulte = difficulte;
        this.dateCreation = dateCreation;
        this.idCelebrite = idCelebrite;
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

    public int getIdCelebrite() {
        return idCelebrite;
    }

    public void setIdCelebrite(int idCelebrite) {
        this.idCelebrite = idCelebrite;
    }

    @Override
    public String toString() {
        return "quiz{" +
                "idQuiz=" + idQuiz +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", difficulte='" + difficulte + '\'' +
                ", dateCreation=" + dateCreation +
                ", idCelebrite=" + idCelebrite +
                '}';
    }
}
