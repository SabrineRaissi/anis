package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.StatusSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StatusSession and its DTO StatusSessionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatusSessionMapper extends EntityMapper<StatusSessionDTO, StatusSession> {



    default StatusSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        StatusSession statusSession = new StatusSession();
        statusSession.setId(id);
        return statusSession;
    }
}
