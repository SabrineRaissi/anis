package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PossibleValue.
 */
@Entity
@Table(name = "possible_value")
public class PossibleValue implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_value")
    private String value;

    @ManyToOne
    @JsonIgnoreProperties("possibleValues")
    private Eligibility eligibility;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public PossibleValue value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Eligibility getEligibility() {
        return eligibility;
    }

    public PossibleValue eligibility(Eligibility eligibility) {
        this.eligibility = eligibility;
        return this;
    }

    public void setEligibility(Eligibility eligibility) {
        this.eligibility = eligibility;
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
        PossibleValue possibleValue = (PossibleValue) o;
        if (possibleValue.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), possibleValue.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PossibleValue{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            "}";
    }
}
