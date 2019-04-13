package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PossibleValueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PossibleValue and its DTO PossibleValueDTO.
 */
@Mapper(componentModel = "spring", uses = {EligibilityMapper.class})
public interface PossibleValueMapper extends EntityMapper<PossibleValueDTO, PossibleValue> {

    @Mapping(source = "eligibility.id", target = "eligibilityId")
    PossibleValueDTO toDto(PossibleValue possibleValue);

    @Mapping(source = "eligibilityId", target = "eligibility")
    PossibleValue toEntity(PossibleValueDTO possibleValueDTO);

    default PossibleValue fromId(Long id) {
        if (id == null) {
            return null;
        }
        PossibleValue possibleValue = new PossibleValue();
        possibleValue.setId(id);
        return possibleValue;
    }
}
