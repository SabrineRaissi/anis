package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LastTheses.
 */
@Entity
@Table(name = "last_theses")
public class LastTheses implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annee_universitaire")
    private String anneeUniversitaire;

    @Column(name = "etablissement")
    private String etablissement;

    @Column(name = "sujet_these")
    private String sujetThese;

    @ManyToOne
    @JsonIgnoreProperties("lastTheses")
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

    public LastTheses anneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
        return this;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public LastTheses etablissement(String etablissement) {
        this.etablissement = etablissement;
        return this;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getSujetThese() {
        return sujetThese;
    }

    public LastTheses sujetThese(String sujetThese) {
        this.sujetThese = sujetThese;
        return this;
    }

    public void setSujetThese(String sujetThese) {
        this.sujetThese = sujetThese;
    }

    public Doctorant getDoctorant() {
        return doctorant;
    }

    public LastTheses doctorant(Doctorant doctorant) {
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
        LastTheses lastTheses = (LastTheses) o;
        if (lastTheses.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lastTheses.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LastTheses{" +
            "id=" + getId() +
            ", anneeUniversitaire='" + getAnneeUniversitaire() + "'" +
            ", etablissement='" + getEtablissement() + "'" +
            ", sujetThese='" + getSujetThese() + "'" +
            "}";
    }
}
