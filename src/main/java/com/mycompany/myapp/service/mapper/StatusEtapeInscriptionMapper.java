package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.StatusEtapeInscriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StatusEtapeInscription and its DTO StatusEtapeInscriptionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatusEtapeInscriptionMapper extends EntityMapper<StatusEtapeInscriptionDTO, StatusEtapeInscription> {



    default StatusEtapeInscription fromId(Long id) {
        if (id == null) {
            return null;
        }
        StatusEtapeInscription statusEtapeInscription = new StatusEtapeInscription();
        statusEtapeInscription.setId(id);
        return statusEtapeInscription;
    }
}
