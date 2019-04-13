package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EtudeUniversitaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EtudeUniversitaire and its DTO EtudeUniversitaireDTO.
 */
@Mapper(componentModel = "spring", uses = {DoctorantMapper.class})
public interface EtudeUniversitaireMapper extends EntityMapper<EtudeUniversitaireDTO, EtudeUniversitaire> {

    @Mapping(source = "doctorant.id", target = "doctorantId")
    EtudeUniversitaireDTO toDto(EtudeUniversitaire etudeUniversitaire);

    @Mapping(source = "doctorantId", target = "doctorant")
    EtudeUniversitaire toEntity(EtudeUniversitaireDTO etudeUniversitaireDTO);

    default EtudeUniversitaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtudeUniversitaire etudeUniversitaire = new EtudeUniversitaire();
        etudeUniversitaire.setId(id);
        return etudeUniversitaire;
    }
}
