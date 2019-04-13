package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PossibleValue entity.
 */
public class PossibleValueDTO implements Serializable {

    private Long id;

    private String value;


    private Long eligibilityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getEligibilityId() {
        return eligibilityId;
    }

    public void setEligibilityId(Long eligibilityId) {
        this.eligibilityId = eligibilityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PossibleValueDTO possibleValueDTO = (PossibleValueDTO) o;
        if (possibleValueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), possibleValueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PossibleValueDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", eligibility=" + getEligibilityId() +
            "}";
    }
}
