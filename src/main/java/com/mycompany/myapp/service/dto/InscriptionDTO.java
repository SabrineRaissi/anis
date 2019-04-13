package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Inscription entity.
 */
public class InscriptionDTO implements Serializable {

    private Long id;


    private Long doctorantId;

    private Long theseId;

    private Long statusInscriptionId;

    private Long nextStepId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorantId() {
        return doctorantId;
    }

    public void setDoctorantId(Long doctorantId) {
        this.doctorantId = doctorantId;
    }

    public Long getTheseId() {
        return theseId;
    }

    public void setTheseId(Long theseId) {
        this.theseId = theseId;
    }

    public Long getStatusInscriptionId() {
        return statusInscriptionId;
    }

    public void setStatusInscriptionId(Long statusInscriptionId) {
        this.statusInscriptionId = statusInscriptionId;
    }

    public Long getNextStepId() {
        return nextStepId;
    }

    public void setNextStepId(Long etapeInscriptionId) {
        this.nextStepId = etapeInscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InscriptionDTO inscriptionDTO = (InscriptionDTO) o;
        if (inscriptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inscriptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InscriptionDTO{" +
            "id=" + getId() +
            ", doctorant=" + getDoctorantId() +
            ", these=" + getTheseId() +
            ", statusInscription=" + getStatusInscriptionId() +
            ", nextStep=" + getNextStepId() +
            "}";
    }
}
