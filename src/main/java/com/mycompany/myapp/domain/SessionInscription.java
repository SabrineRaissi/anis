package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.mycompany.myapp.domain.enumeration.Annee;

/**
 * A SessionInscription.
 */
@Entity
@Table(name = "session_inscription")
public class SessionInscription implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "annee")
    private Annee annee;

    @OneToOne
    @JoinColumn(unique = true)
    private These these;

    @OneToOne
    @JoinColumn(unique = true)
    private Etablissement etablissement;

    @OneToOne
    @JoinColumn(unique = true)
    private StatusSession statusSession;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public SessionInscription startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public SessionInscription endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Annee getAnnee() {
        return annee;
    }

    public SessionInscription annee(Annee annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public These getThese() {
        return these;
    }

    public SessionInscription these(These these) {
        this.these = these;
        return this;
    }

    public void setThese(These these) {
        this.these = these;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public SessionInscription etablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
        return this;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public StatusSession getStatusSession() {
        return statusSession;
    }

    public SessionInscription statusSession(StatusSession statusSession) {
        this.statusSession = statusSession;
        return this;
    }

    public void setStatusSession(StatusSession statusSession) {
        this.statusSession = statusSession;
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
        SessionInscription sessionInscription = (SessionInscription) o;
        if (sessionInscription.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sessionInscription.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SessionInscription{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", annee='" + getAnnee() + "'" +
            "}";
    }
}
