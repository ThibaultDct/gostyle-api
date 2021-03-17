package fr.epsi.gostyleapi.external.dto;

import java.util.Objects;

public class UserDTO {

    private String nom;
    private String prenom;
    private String pseudo;
    private String mail;
    private String mdp;

    public UserDTO() {}

    public UserDTO(String nom, String prenom, String pseudo, String mail, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getNom(), userDTO.getNom()) &&
                Objects.equals(getPrenom(), userDTO.getPrenom()) &&
                Objects.equals(getPseudo(), userDTO.getPseudo()) &&
                Objects.equals(getMail(), userDTO.getMail()) &&
                Objects.equals(getMdp(), userDTO.getMdp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getPrenom(), getPseudo(), getMail(), getMdp());
    }
}
