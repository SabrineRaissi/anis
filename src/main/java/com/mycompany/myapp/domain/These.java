package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A These.
 */
@Entity
@Table(name = "these")
public class These implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designation")
    private String designation;

    @OneToOne
    @JoinColumn(unique = true)
    private Etablissement etablissement;

    @OneToMany(mappedBy = "these")
    private Set<Eligibility> eligibilites = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public These designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public These etablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
        return this;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public Set<Eligibility> getEligibilites() {
        return eligibilites;
    }

    public These eligibilites(Set<Eligibility> eligibilities) {
        this.eligibilites = eligibilities;
        return this;
    }

    public These addEligibilites(Eligibility eligibility) {
        this.eligibilites.add(eligibility);
        eligibility.setThese(this);
        return this;
    }

    public These removeEligibilites(Eligibility eligibility) {
        this.eligibilites.remove(eligibility);
        eligibility.setThese(null);
        return this;
    }

    public void setEligibilites(Set<Eligibility> eligibilities) {
        this.eligibilites = eligibilities;
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
        These these = (These) o;
        if (these.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), these.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "These{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            "}";
    }
}
