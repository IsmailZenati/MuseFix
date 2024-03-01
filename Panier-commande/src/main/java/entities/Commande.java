package entities;

import java.util.Date;

public class Commande {
    private int idCommande;
    private int userID;
    private Date orderDate;
    private String status;
    private String modePaiement;
    private String adresseLivraison;
    private float fraisLivraison;
    private float total;

    // Constructor
    public Commande(int userID, Date orderDate, String status, String modePaiement, String adresseLivraison, float fraisLivraison, float total) {
        this.userID = userID;
        this.orderDate = orderDate;
        this.status = status;
        this.modePaiement = modePaiement;
        this.adresseLivraison = adresseLivraison;
        this.fraisLivraison = fraisLivraison;
        this.total = total;
    }

    // Getters and Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public float getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(float fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", userID=" + userID +
                ", dateCommande='" + orderDate + '\'' +
                ", statut='" + status + '\'' +
                ", modePaiement='" + modePaiement + '\'' +
                ", adresseLivraison='" + adresseLivraison + '\'' +
                ", fraisLivraison=" + fraisLivraison +
                ", total=" + total +
                '}';
    }


}
