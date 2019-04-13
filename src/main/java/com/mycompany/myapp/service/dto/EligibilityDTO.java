package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Eligibility entity.
 */
public class EligibilityDTO implements Serializable {

    private Long id;

    private String criteria;

    private Float score;


    private Long etablissementId;

    private Long theseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Long getEtablissementId() {
        return etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }

    public Long getTheseId() {
        return theseId;
    }

    public void setTheseId(Long theseId) {
        this.theseId = theseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EligibilityDTO eligibilityDTO = (EligibilityDTO) o;
        if (eligibilityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eligibilityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EligibilityDTO{" +
            "id=" + getId() +
            ", criteria='" + getCriteria() + "'" +
            ", score=" + getScore() +
            ", etablissement=" + getEtablissementId() +
            ", these=" + getTheseId() +
            "}";
    }
}
