package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.SessionInscriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SessionInscription and its DTO SessionInscriptionDTO.
 */
@Mapper(componentModel = "spring", uses = {TheseMapper.class, EtablissementMapper.class, StatusSessionMapper.class})
public interface SessionInscriptionMapper extends EntityMapper<SessionInscriptionDTO, SessionInscription> {

    @Mapping(source = "these.id", target = "theseId")
    @Mapping(source = "etablissement.id", target = "etablissementId")
    @Mapping(source = "statusSession.id", target = "statusSessionId")
    SessionInscriptionDTO toDto(SessionInscription sessionInscription);

    @Mapping(source = "theseId", target = "these")
    @Mapping(source = "etablissementId", target = "etablissement")
    @Mapping(source = "statusSessionId", target = "statusSession")
    SessionInscription toEntity(SessionInscriptionDTO sessionInscriptionDTO);

    default SessionInscription fromId(Long id) {
        if (id == null) {
            return null;
        }
        SessionInscription sessionInscription = new SessionInscription();
        sessionInscription.setId(id);
        return sessionInscription;
    }
}
