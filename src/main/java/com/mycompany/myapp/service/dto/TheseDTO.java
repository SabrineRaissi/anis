package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the These entity.
 */
public class TheseDTO implements Serializable {

    private Long id;

    private String designation;


    private Long etablissementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getEtablissementId() {
        return etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TheseDTO theseDTO = (TheseDTO) o;
        if (theseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), theseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TheseDTO{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            ", etablissement=" + getEtablissementId() +
            "}";
    }
}
