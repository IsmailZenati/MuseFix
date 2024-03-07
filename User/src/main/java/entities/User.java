package entities;
import java.util.Date;


public class User {
    private int id;

    private String email;
    private String password;

    private String nom; // Name
    private String prenom; // Last name
    private String role;
    private Date signupDate;
    private String adresse;

    private int tel;

    public User() {
    }

    public User(int id, String email, String password, String nom, String prenom, String role, Date signupDate, String adresse, int tel) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.signupDate = signupDate;
        this.adresse = adresse;
        this.tel = tel;
    }



    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Date getSignupDate() { return signupDate; }
    public int getTel() { return tel; }
    public void setTel(int tel) { this.tel = tel; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String  adresse) { this.adresse = adresse; }
    public void setSignupDate(Date signupDate) { this.signupDate = signupDate; }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nom=" + nom +
                ", prenom='" + prenom + '\'' +
                ", role='" + role + '\'' +
                ", signup date='" + signupDate + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

}





