package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StatusInscription entity.
 */
public class StatusInscriptionDTO implements Serializable {

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

        StatusInscriptionDTO statusInscriptionDTO = (StatusInscriptionDTO) o;
        if (statusInscriptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statusInscriptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatusInscriptionDTO{" +
            "id=" + getId() +
            "}";
    }
}
