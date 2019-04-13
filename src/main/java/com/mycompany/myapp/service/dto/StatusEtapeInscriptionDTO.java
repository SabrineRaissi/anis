package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StatusEtapeInscription entity.
 */
public class StatusEtapeInscriptionDTO implements Serializable {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatusEtapeInscriptionDTO statusEtapeInscriptionDTO = (StatusEtapeInscriptionDTO) o;
        if (statusEtapeInscriptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statusEtapeInscriptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatusEtapeInscriptionDTO{" +
            "id=" + getId() +
            "}";
    }
}
