package entities;

public class packs {
    private  String avantages, typePack;
    private Integer  IdPack ;
    private Float Prix;
    public packs() {

    }

    public packs(String typePack , Float Prix ,String avantages){
        this.Prix=Prix;
        this.avantages=avantages;
        this.typePack=typePack;
    }

    public Integer getIdPack(){
        return this.IdPack;

    }
    public void setIdPack(Integer IdPack) {
        this.IdPack = IdPack;
    }
    public String getTypePack(){
        return this.typePack;
    }
    public void setTypePack(String typePack){
        this.typePack= typePack;

    }
    public float getPrix() {
        return this.Prix;
    }
    public void setPrix(Float Prix){

        this.Prix=Prix;
    }
    public String getAvantages(){
        return this.avantages;
    }

    public void setAvantages(String avantages){
        this.avantages=avantages;
    }

    public packs(int idPack , String typePack , float prix , String avantages) {
        this.avantages = avantages;
        this.typePack = typePack;
       this.IdPack = idPack;
        this.Prix = prix;
    }

    public String toString() {
        return "Pack ID: " + this.IdPack + "\n" +
                "Type: " + this.typePack + "\n" +
                "Prix: " + this.Prix + "\n" +
                "Avantages: " + this.avantages;
    }

}