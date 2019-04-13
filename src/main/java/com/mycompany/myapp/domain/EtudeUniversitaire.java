package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EtudeUniversitaire.
 */
@Entity
@Table(name = "etude_universitaire")
public class EtudeUniversitaire implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annee_universitaire")
    private String anneeUniversitaire;

    @Column(name = "etablissement")
    private String etablissement;

    @Column(name = "diplome")
    private String diplome;

    @Column(name = "niveau_etude")
    private String niveauEtude;

    @Column(name = "remarque")
    private String remarque;

    @ManyToOne
    @JsonIgnoreProperties("etudeUniversitaires")
    private Doctorant doctorant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public EtudeUniversitaire anneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
        return this;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public EtudeUniversitaire etablissement(String etablissement) {
        this.etablissement = etablissement;
        return this;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getDiplome() {
        return diplome;
    }

    public EtudeUniversitaire diplome(String diplome) {
        this.diplome = diplome;
        return this;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public EtudeUniversitaire niveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
        return this;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public String getRemarque() {
        return remarque;
    }

    public EtudeUniversitaire remarque(String remarque) {
        this.remarque = remarque;
        return this;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Doctorant getDoctorant() {
        return doctorant;
    }

    public EtudeUniversitaire doctorant(Doctorant doctorant) {
        this.doctorant = doctorant;
        return this;
    }

    public void setDoctorant(Doctorant doctorant) {
        this.doctorant = doctorant;
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
        EtudeUniversitaire etudeUniversitaire = (EtudeUniversitaire) o;
        if (etudeUniversitaire.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etudeUniversitaire.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtudeUniversitaire{" +
            "id=" + getId() +
            ", anneeUniversitaire='" + getAnneeUniversitaire() + "'" +
            ", etablissement='" + getEtablissement() + "'" +
            ", diplome='" + getDiplome() + "'" +
            ", niveauEtude='" + getNiveauEtude() + "'" +
            ", remarque='" + getRemarque() + "'" +
            "}";
    }
}
