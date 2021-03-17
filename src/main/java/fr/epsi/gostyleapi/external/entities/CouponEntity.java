package fr.epsi.gostyleapi.external.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "coupon", schema = "public")
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String emplacement;
    private Integer nb_utilisation;
    private String libelle;
    private Integer pourc_reduc;

    @Id
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
    @Column(name = "nb_utilisation")
    public Integer getNbUtilisation() {
        return nb_utilisation;
    }

    public void setNbUtilisation(Integer nbUtilisation) {
        this.nb_utilisation = nbUtilisation;
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
    @Column(name = "pourc_reduc")
    public Integer getPourcReduc() {
        return pourc_reduc;
    }

    public void setPourcReduc(Integer pourc_reduc) {
        this.pourc_reduc = pourc_reduc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponEntity that = (CouponEntity) o;
        return id == that.id &&
                nb_utilisation == that.nb_utilisation &&
                pourc_reduc == that.pourc_reduc &&
                Objects.equals(emplacement, that.emplacement) &&
                Objects.equals(libelle, that.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emplacement, nb_utilisation, libelle, pourc_reduc);
    }
}
