package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EtudeUniversitaire entity.
 */
public class EtudeUniversitaireDTO implements Serializable {

    private Long id;

    private String anneeUniversitaire;

    private String etablissement;

    private String diplome;

    private String niveauEtude;

    private String remarque;


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

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
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

        EtudeUniversitaireDTO etudeUniversitaireDTO = (EtudeUniversitaireDTO) o;
        if (etudeUniversitaireDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etudeUniversitaireDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtudeUniversitaireDTO{" +
            "id=" + getId() +
            ", anneeUniversitaire='" + getAnneeUniversitaire() + "'" +
            ", etablissement='" + getEtablissement() + "'" +
            ", diplome='" + getDiplome() + "'" +
            ", niveauEtude='" + getNiveauEtude() + "'" +
            ", remarque='" + getRemarque() + "'" +
            ", doctorant=" + getDoctorantId() +
            "}";
    }
}
