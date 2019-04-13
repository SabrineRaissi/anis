package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Doctorant.
 */
@Entity
@Table(name = "doctorant")
public class Doctorant implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nationalite")
    private String nationalite;

    @Column(name = "date_nissance")
    private ZonedDateTime dateNissance;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "etat_civil")
    private String etatCivil;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "profession")
    private String profession;

    @Column(name = "employeur")
    private String employeur;

    @Lob
    @Column(name = "profile_pic")
    private byte[] profilePic;

    @Column(name = "profile_pic_content_type")
    private String profilePicContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalite() {
        return nationalite;
    }

    public Doctorant nationalite(String nationalite) {
        this.nationalite = nationalite;
        return this;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public ZonedDateTime getDateNissance() {
        return dateNissance;
    }

    public Doctorant dateNissance(ZonedDateTime dateNissance) {
        this.dateNissance = dateNissance;
        return this;
    }

    public void setDateNissance(ZonedDateTime dateNissance) {
        this.dateNissance = dateNissance;
    }

    public String getSexe() {
        return sexe;
    }

    public Doctorant sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEtatCivil() {
        return etatCivil;
    }

    public Doctorant etatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
        return this;
    }

    public void setEtatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
    }

    public String getAdresse() {
        return adresse;
    }

    public Doctorant adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfession() {
        return profession;
    }

    public Doctorant profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmployeur() {
        return employeur;
    }

    public Doctorant employeur(String employeur) {
        this.employeur = employeur;
        return this;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public Doctorant profilePic(byte[] profilePic) {
        this.profilePic = profilePic;
        return this;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePicContentType() {
        return profilePicContentType;
    }

    public Doctorant profilePicContentType(String profilePicContentType) {
        this.profilePicContentType = profilePicContentType;
        return this;
    }

    public void setProfilePicContentType(String profilePicContentType) {
        this.profilePicContentType = profilePicContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Doctorant doctorant = (Doctorant) o;
        if (doctorant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), doctorant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Doctorant{" +
            "id=" + getId() +
            ", nationalite='" + getNationalite() + "'" +
            ", dateNissance='" + getDateNissance() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", etatCivil='" + getEtatCivil() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", profession='" + getProfession() + "'" +
            ", employeur='" + getEmployeur() + "'" +
            ", profilePic='" + getProfilePic() + "'" +
            ", profilePicContentType='" + getProfilePicContentType() + "'" +
            "}";
    }
}
