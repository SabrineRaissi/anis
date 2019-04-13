package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LastTheses entity.
 */
public class LastThesesDTO implements Serializable {

    private Long id;

    private String anneeUniversitaire;

    private String etablissement;

    private String sujetThese;


    private Long doctorantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getSujetThese() {
        return sujetThese;
    }

    public void setSujetThese(String sujetThese) {
        this.sujetThese = sujetThese;
    }

    public Long getDoctorantId() {
        return doctorantId;
    }

    public void setDoctorantId(Long doctorantId) {
        this.doctorantId = doctorantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LastThesesDTO lastThesesDTO = (LastThesesDTO) o;
        if (lastThesesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lastThesesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LastThesesDTO{" +
            "id=" + getId() +
            ", anneeUniversitaire='" + getAnneeUniversitaire() + "'" +
            ", etablissement='" + getEtablissement() + "'" +
            ", sujetThese='" + getSujetThese() + "'" +
            ", doctorant=" + getDoctorantId() +
            "}";
    }
}
