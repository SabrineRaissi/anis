package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LastThesesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LastTheses and its DTO LastThesesDTO.
 */
@Mapper(componentModel = "spring", uses = {DoctorantMapper.class})
public interface LastThesesMapper extends EntityMapper<LastThesesDTO, LastTheses> {

    @Mapping(source = "doctorant.id", target = "doctorantId")
    LastThesesDTO toDto(LastTheses lastTheses);

    @Mapping(source = "doctorantId", target = "doctorant")
    LastTheses toEntity(LastThesesDTO lastThesesDTO);

    default LastTheses fromId(Long id) {
        if (id == null) {
            return null;
        }
        LastTheses lastTheses = new LastTheses();
        lastTheses.setId(id);
        return lastTheses;
    }
}
