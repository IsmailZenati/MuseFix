package entities;
import java.util.Date;

public class quiz {
    private int idQuiz ,idCelebrite;
    private String titre ,description;
    private String difficulte;
    private Date DateCreation;
    public quiz() {
    }

    public quiz(int idQuiz , String titre , String description, String difficulte, Date DateCreation, int idCelebrite) {
        this.idQuiz = idQuiz;
        this.titre = titre;
        this.description = description;
        this.difficulte = difficulte;
        this.DateCreation = DateCreation;
        this.idCelebrite = idCelebrite;
    }

    public int getidQuiz() {
        return idQuiz;
    }

    public void setidQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String gettitre() {
        return titre;
    }

    public void settitre(String titre) {
        this.titre = titre;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getdifficulte() { return difficulte; }

    public void setdifficulte(String difficulte) {this.difficulte = difficulte;}

    public Date getDateCreation() {return DateCreation;}
    public void setDateCreation(Date DateCreation){this.DateCreation = DateCreation;}

    public int getidCelebrite() {
        return idCelebrite;
    }

    public void setidCelebrite(int idCelebrite) {
        this.idCelebrite = idCelebrite;
    }


    @Override
    public String toString() {
        return "quiz{" +
                "idQuiz=" + idQuiz + '\'' +
                ", titre=" + titre + '\'' +
                ", description='" + description + '\'' +
                ", difficulte='" + difficulte + '\'' +
                ", DateCreation='" + DateCreation + '\'' +
                ", idCelebrite='" + idCelebrite + '\'' +
                '}';
    }
}
