package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DoctorantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Doctorant and its DTO DoctorantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DoctorantMapper extends EntityMapper<DoctorantDTO, Doctorant> {



    default Doctorant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Doctorant doctorant = new Doctorant();
        doctorant.setId(id);
        return doctorant;
    }
}
