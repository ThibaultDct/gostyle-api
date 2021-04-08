package fr.epsi.gostyleapi.external.dto;

import java.util.Objects;

public class CouponDTO {

    private String code;
    private String emplacement;
    private Integer nb_utilisation;
    private String libelle;
    private Integer pourc_reduc;

    public CouponDTO() {}

    public CouponDTO(String code, String emplacement, Integer nb_utilisation, String libelle, Integer pourc_reduc) {
        this.code = code;
        this.emplacement = emplacement;
        this.nb_utilisation = nb_utilisation;
        this.libelle = libelle;
        this.pourc_reduc = pourc_reduc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public Integer getNb_utilisation() {
        return nb_utilisation;
    }

    public void setNb_utilisation(Integer nb_utilisation) {
        this.nb_utilisation = nb_utilisation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

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
        CouponDTO couponDTO = (CouponDTO) o;
        return Objects.equals(getCode(), couponDTO.getCode()) &&
                Objects.equals(getEmplacement(), couponDTO.getEmplacement()) &&
                Objects.equals(getNb_utilisation(), couponDTO.getNb_utilisation()) &&
                Objects.equals(getLibelle(), couponDTO.getLibelle()) &&
                Objects.equals(getPourc_reduc(), couponDTO.getPourc_reduc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getEmplacement(), getNb_utilisation(), getLibelle(), getPourc_reduc());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ 'code': '");
        sb.append(this.getCode());
        sb.append(", 'emplacement': '");
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
