package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A StatusEtapeInscription.
 */
@Entity
@Table(name = "status_etape_inscription")
public class StatusEtapeInscription implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        StatusEtapeInscription statusEtapeInscription = (StatusEtapeInscription) o;
        if (statusEtapeInscription.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statusEtapeInscription.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatusEtapeInscription{" +
            "id=" + getId() +
            "}";
    }
}
