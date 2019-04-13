package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EtapeInscription entity.
 */
public class EtapeInscriptionDTO implements Serializable {

    private Long id;

    private String etape;

    private String description;


    private Long nextId;

    private Long previousId;

    private Long statusEtapeId;

    private Long etablissementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtape() {
        return etape;
    }

    public void setEtape(String etape) {
        this.etape = etape;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long etapeInscriptionId) {
        this.nextId = etapeInscriptionId;
    }

    public Long getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Long etapeInscriptionId) {
        this.previousId = etapeInscriptionId;
    }

    public Long getStatusEtapeId() {
        return statusEtapeId;
    }

    public void setStatusEtapeId(Long statusEtapeInscriptionId) {
        this.statusEtapeId = statusEtapeInscriptionId;
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

        EtapeInscriptionDTO etapeInscriptionDTO = (EtapeInscriptionDTO) o;
        if (etapeInscriptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etapeInscriptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtapeInscriptionDTO{" +
            "id=" + getId() +
            ", etape='" + getEtape() + "'" +
            ", description='" + getDescription() + "'" +
            ", next=" + getNextId() +
            ", previous=" + getPreviousId() +
            ", statusEtape=" + getStatusEtapeId() +
            ", etablissement=" + getEtablissementId() +
            "}";
    }
}
