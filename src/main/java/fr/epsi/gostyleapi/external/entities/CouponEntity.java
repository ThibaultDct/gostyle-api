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
    private String code;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public Integer getNb_utilisation() {
        return nb_utilisation;
    }

    public void setNb_utilisation(Integer nbUtilisation) {
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
    public Integer getPourc_reduc() {
        return pourc_reduc;
    }

    public void setPourc_reduc(Integer pourc_reduc) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ 'id': '");
        sb.append(this.getId());
        sb.append("', 'code': '");
        sb.append(this.getCode());
        sb.append("', 'emplacement': '");
        sb.append(this.getEmplacement());
        sb.append("', 'nb_utilisation': ");
        sb.append(this.getNb_utilisation());
        sb.append(", 'libelle': '");
        sb.append(this.getLibelle());
        sb.append("', 'pourc_reduc': ");
        sb.append(this.getPourc_reduc());
        sb.append(" }");
        return sb.toString();
    }
}
