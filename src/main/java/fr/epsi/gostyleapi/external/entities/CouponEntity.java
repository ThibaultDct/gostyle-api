package fr.epsi.gostyleapi.external.entities;

import fr.epsi.gostyleapi.business.Categorie;
import fr.epsi.gostyleapi.business.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "coupon", schema = "gostyle", catalog = "")
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String emplacement;
    private int nbUtilisation;
    private String libelle;
    private Categorie categorie;
    private int pourReduc;
    private User users;
    private int pourcReduc;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "emplacement")
    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    @Basic
    @Column(name = "nbUtilisation")
    public int getNbUtilisation() {
        return nbUtilisation;
    }

    public void setNbUtilisation(int nbUtilisation) {
        this.nbUtilisation = nbUtilisation;
    }

    @Basic
    @Column(name = "libelle")
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Basic
    @Column(name = "pourcReduc")
    public int getPourcReduc() {
        return pourcReduc;
    }

    public void setPourcReduc(int pourcReduc) {
        this.pourcReduc = pourcReduc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponEntity that = (CouponEntity) o;
        return id == that.id &&
                nbUtilisation == that.nbUtilisation &&
                pourcReduc == that.pourcReduc &&
                Objects.equals(emplacement, that.emplacement) &&
                Objects.equals(libelle, that.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emplacement, nbUtilisation, libelle, pourcReduc);
    }
}
