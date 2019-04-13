package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.StatusInscriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StatusInscription and its DTO StatusInscriptionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatusInscriptionMapper extends EntityMapper<StatusInscriptionDTO, StatusInscription> {



    default StatusInscription fromId(Long id) {
        if (id == null) {
            return null;
        }
        StatusInscription statusInscription = new StatusInscription();
        statusInscription.setId(id);
        return statusInscription;
    }
}
