package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EtapeInscription.
 */
@Entity
@Table(name = "etape_inscription")
public class EtapeInscription implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "etape")
    private String etape;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(unique = true)
    private EtapeInscription next;

    @OneToOne
    @JoinColumn(unique = true)
    private EtapeInscription previous;

    @OneToOne
    @JoinColumn(unique = true)
    private StatusEtapeInscription statusEtape;

    @OneToOne
    @JoinColumn(unique = true)
    private Etablissement etablissement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtape() {
        return etape;
    }

    public EtapeInscription etape(String etape) {
        this.etape = etape;
        return this;
    }

    public void setEtape(String etape) {
        this.etape = etape;
    }

    public String getDescription() {
        return description;
    }

    public EtapeInscription description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EtapeInscription getNext() {
        return next;
    }

    public EtapeInscription next(EtapeInscription etapeInscription) {
        this.next = etapeInscription;
        return this;
    }

    public void setNext(EtapeInscription etapeInscription) {
        this.next = etapeInscription;
    }

    public EtapeInscription getPrevious() {
        return previous;
    }

    public EtapeInscription previous(EtapeInscription etapeInscription) {
        this.previous = etapeInscription;
        return this;
    }

    public void setPrevious(EtapeInscription etapeInscription) {
        this.previous = etapeInscription;
    }

    public StatusEtapeInscription getStatusEtape() {
        return statusEtape;
    }

    public EtapeInscription statusEtape(StatusEtapeInscription statusEtapeInscription) {
        this.statusEtape = statusEtapeInscription;
        return this;
    }

    public void setStatusEtape(StatusEtapeInscription statusEtapeInscription) {
        this.statusEtape = statusEtapeInscription;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public EtapeInscription etablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
        return this;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
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
        EtapeInscription etapeInscription = (EtapeInscription) o;
        if (etapeInscription.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etapeInscription.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtapeInscription{" +
            "id=" + getId() +
            ", etape='" + getEtape() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
