package fr.epsi.gostyleapi.external.dto;

import java.util.Objects;
import java.util.UUID;

public class QueryCouponDTO {

    private UUID id;
    private String code;
    private Integer pourc_reduc;
    private String libelle;
    private String date;

    public QueryCouponDTO() {
    }

    public QueryCouponDTO(UUID id, String code, Integer pourc_reduc, String libelle, String date) {
        this.id = id;
        this.code = code;
        this.pourc_reduc = pourc_reduc;
        this.libelle = libelle;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPourc_reduc() {
        return pourc_reduc;
    }

    public void setPourc_reduc(Integer pourc_reduc) {
        this.pourc_reduc = pourc_reduc;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryCouponDTO that = (QueryCouponDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getPourc_reduc(), that.getPourc_reduc()) &&
                Objects.equals(getLibelle(), that.getLibelle()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getPourc_reduc(), getLibelle(), getDate());
    }
}
