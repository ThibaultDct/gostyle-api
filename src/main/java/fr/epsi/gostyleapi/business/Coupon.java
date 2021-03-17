package fr.epsi.gostyleapi.business;

import java.util.UUID;

public class Coupon {

    private UUID id;
    private String emplacement;
    private int nbUtilisation;
    private String libelle;
    private Categorie categorie;
    private int pourcReduc;
    private User user;

    public Coupon(){}

    public Coupon(UUID id, String emplacement, int nbUtilisation, String libelle, Categorie categorie, int pourcReduc, User user) {
        this.id = id;
        this.emplacement = emplacement;
        this.nbUtilisation = nbUtilisation;
        this.libelle = libelle;
        this.categorie = categorie;
        this.pourcReduc = pourcReduc;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getNbUtilisation() {
        return nbUtilisation;
    }

    public void setNbUtilisation(int nbUtilisation) {
        this.nbUtilisation = nbUtilisation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getPourcReduc() {
        return pourcReduc;
    }

    public void setPourcReduc(int pourcReduc) {
        this.pourcReduc = pourcReduc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
