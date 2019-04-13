package com.mycompany.myapp.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Doctorant entity.
 */
public class DoctorantDTO implements Serializable {

    private Long id;

    private String nationalite;

    private ZonedDateTime dateNissance;

    private String sexe;

    private String etatCivil;

    private String adresse;

    private String profession;

    private String employeur;

    @Lob
    private byte[] profilePic;

    private String profilePicContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public ZonedDateTime getDateNissance() {
        return dateNissance;
    }

    public void setDateNissance(ZonedDateTime dateNissance) {
        this.dateNissance = dateNissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEtatCivil() {
        return etatCivil;
    }

    public void setEtatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmployeur() {
        return employeur;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePicContentType() {
        return profilePicContentType;
    }

    public void setProfilePicContentType(String profilePicContentType) {
        this.profilePicContentType = profilePicContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DoctorantDTO doctorantDTO = (DoctorantDTO) o;
        if (doctorantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), doctorantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DoctorantDTO{" +
            "id=" + getId() +
            ", nationalite='" + getNationalite() + "'" +
            ", dateNissance='" + getDateNissance() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", etatCivil='" + getEtatCivil() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", profession='" + getProfession() + "'" +
            ", employeur='" + getEmployeur() + "'" +
            ", profilePic='" + getProfilePic() + "'" +
            "}";
    }
}
