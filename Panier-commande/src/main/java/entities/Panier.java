package entities;

public class Panier {
    private int idPanier;
    private int userID;
    private int idProduit;
    private int qte;
    private float prixUnite;
    private float sousTotal;

    // Constructeur
    public Panier(int userID, int idProduit, int qte, float prixUnite) {
        this.userID = userID;
        this.idProduit = idProduit;
        this.qte = qte;
        this.prixUnite = prixUnite;
        this.sousTotal = qte * prixUnite;
    }
    public Panier(int idPanier,int userID, int idProduit, int qte, float prixUnite) {
        this.idPanier = idPanier;
        this.userID = userID;
        this.idProduit = idProduit;
        this.qte = qte;
        this.prixUnite = prixUnite;
        this.sousTotal = qte * prixUnite;
    }


    // Getters et Setters
    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getPrixUnite() {
        return prixUnite;
    }

    public void setPrixUnite(float prixUnite) {
        this.prixUnite = prixUnite;
    }

    public float getSousTotal() {
        return sousTotal;
    }

    public void setSousTotal(float sousTotal) {
        this.sousTotal = sousTotal;
    }
    @Override
    public String toString() {
        return "Panier{" +
                "idPanier=" + idPanier +
                ", userID=" + userID +
                ", idProduit=" + idProduit +
                ", quantite=" + qte +
                ", prixUnite=" + prixUnite +
                ", sousTotal=" + sousTotal +
                '}';
    }
}
