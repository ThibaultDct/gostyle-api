package fr.epsi.gostyleapi.external.dto;

import java.util.Objects;

public class CategorieDTO {

    private String nom;

    public CategorieDTO() {}

    public CategorieDTO(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieDTO that = (CategorieDTO) o;
        return Objects.equals(getNom(), that.getNom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom());
    }
}
