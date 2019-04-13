package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Inscription.
 */
@Entity
@Table(name = "inscription")
public class Inscription implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private Doctorant doctorant;

    @OneToOne
    @JoinColumn(unique = true)
    private These these;

    @OneToOne
    @JoinColumn(unique = true)
    private StatusInscription statusInscription;

    @OneToOne
    @JoinColumn(unique = true)
    private EtapeInscription nextStep;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctorant getDoctorant() {
        return doctorant;
    }

    public Inscription doctorant(Doctorant doctorant) {
        this.doctorant = doctorant;
        return this;
    }

    public void setDoctorant(Doctorant doctorant) {
        this.doctorant = doctorant;
    }

    public These getThese() {
        return these;
    }

    public Inscription these(These these) {
        this.these = these;
        return this;
    }

    public void setThese(These these) {
        this.these = these;
    }

    public StatusInscription getStatusInscription() {
        return statusInscription;
    }

    public Inscription statusInscription(StatusInscription statusInscription) {
        this.statusInscription = statusInscription;
        return this;
    }

    public void setStatusInscription(StatusInscription statusInscription) {
        this.statusInscription = statusInscription;
    }

    public EtapeInscription getNextStep() {
        return nextStep;
    }

    public Inscription nextStep(EtapeInscription etapeInscription) {
        this.nextStep = etapeInscription;
        return this;
    }

    public void setNextStep(EtapeInscription etapeInscription) {
        this.nextStep = etapeInscription;
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
        Inscription inscription = (Inscription) o;
        if (inscription.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inscription.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Inscription{" +
            "id=" + getId() +
            "}";
    }
}
