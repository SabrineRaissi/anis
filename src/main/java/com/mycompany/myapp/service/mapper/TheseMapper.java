package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TheseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity These and its DTO TheseDTO.
 */
@Mapper(componentModel = "spring", uses = {EtablissementMapper.class})
public interface TheseMapper extends EntityMapper<TheseDTO, These> {

    @Mapping(source = "etablissement.id", target = "etablissementId")
    TheseDTO toDto(These these);

    @Mapping(source = "etablissementId", target = "etablissement")
    @Mapping(target = "eligibilites", ignore = true)
    These toEntity(TheseDTO theseDTO);

    default These fromId(Long id) {
        if (id == null) {
            return null;
        }
        These these = new These();
        these.setId(id);
        return these;
    }
}
