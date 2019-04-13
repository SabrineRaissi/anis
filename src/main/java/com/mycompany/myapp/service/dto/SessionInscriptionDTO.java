package com.mycompany.myapp.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Annee;

/**
 * A DTO for the SessionInscription entity.
 */
public class SessionInscriptionDTO implements Serializable {

    private Long id;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private Annee annee;


    private Long theseId;

    private Long etablissementId;

    private Long statusSessionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Long getTheseId() {
        return theseId;
    }

    public void setTheseId(Long theseId) {
        this.theseId = theseId;
    }

    public Long getEtablissementId() {
        return etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }

    public Long getStatusSessionId() {
        return statusSessionId;
    }

    public void setStatusSessionId(Long statusSessionId) {
        this.statusSessionId = statusSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SessionInscriptionDTO sessionInscriptionDTO = (SessionInscriptionDTO) o;
        if (sessionInscriptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sessionInscriptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SessionInscriptionDTO{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", annee='" + getAnnee() + "'" +
            ", these=" + getTheseId() +
            ", etablissement=" + getEtablissementId() +
            ", statusSession=" + getStatusSessionId() +
            "}";
    }
}
