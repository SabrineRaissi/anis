package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EtablissementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Etablissement and its DTO EtablissementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtablissementMapper extends EntityMapper<EtablissementDTO, Etablissement> {



    default Etablissement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Etablissement etablissement = new Etablissement();
        etablissement.setId(id);
        return etablissement;
    }
}
