package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EligibilityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Eligibility and its DTO EligibilityDTO.
 */
@Mapper(componentModel = "spring", uses = {EtablissementMapper.class, TheseMapper.class})
public interface EligibilityMapper extends EntityMapper<EligibilityDTO, Eligibility> {

    @Mapping(source = "etablissement.id", target = "etablissementId")
    @Mapping(source = "these.id", target = "theseId")
    EligibilityDTO toDto(Eligibility eligibility);

    @Mapping(source = "etablissementId", target = "etablissement")
    @Mapping(target = "possibleValues", ignore = true)
    @Mapping(source = "theseId", target = "these")
    Eligibility toEntity(EligibilityDTO eligibilityDTO);

    default Eligibility fromId(Long id) {
        if (id == null) {
            return null;
        }
        Eligibility eligibility = new Eligibility();
        eligibility.setId(id);
        return eligibility;
    }
}
