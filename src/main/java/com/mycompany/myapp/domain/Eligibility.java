package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Eligibility.
 */
@Entity
@Table(name = "eligibility")
public class Eligibility implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "score")
    private Float score;

    @OneToOne
    @JoinColumn(unique = true)
    private Etablissement etablissement;

    @OneToMany(mappedBy = "eligibility")
    private Set<PossibleValue> possibleValues = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("eligibilites")
    private These these;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCriteria() {
        return criteria;
    }

    public Eligibility criteria(String criteria) {
        this.criteria = criteria;
        return this;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Float getScore() {
        return score;
    }

    public Eligibility score(Float score) {
        this.score = score;
        return this;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public Eligibility etablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
        return this;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public Set<PossibleValue> getPossibleValues() {
        return possibleValues;
    }

    public Eligibility possibleValues(Set<PossibleValue> possibleValues) {
        this.possibleValues = possibleValues;
        return this;
    }

    public Eligibility addPossibleValues(PossibleValue possibleValue) {
        this.possibleValues.add(possibleValue);
        possibleValue.setEligibility(this);
        return this;
    }

    public Eligibility removePossibleValues(PossibleValue possibleValue) {
        this.possibleValues.remove(possibleValue);
        possibleValue.setEligibility(null);
        return this;
    }

    public void setPossibleValues(Set<PossibleValue> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public These getThese() {
        return these;
    }

    public Eligibility these(These these) {
        this.these = these;
        return this;
    }

    public void setThese(These these) {
        this.these = these;
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
        Eligibility eligibility = (Eligibility) o;
        if (eligibility.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eligibility.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Eligibility{" +
            "id=" + getId() +
            ", criteria='" + getCriteria() + "'" +
            ", score=" + getScore() +
            "}";
    }
}
