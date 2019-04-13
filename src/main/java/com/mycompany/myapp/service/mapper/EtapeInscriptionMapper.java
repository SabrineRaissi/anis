package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EtapeInscriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EtapeInscription and its DTO EtapeInscriptionDTO.
 */
@Mapper(componentModel = "spring", uses = {StatusEtapeInscriptionMapper.class, EtablissementMapper.class})
public interface EtapeInscriptionMapper extends EntityMapper<EtapeInscriptionDTO, EtapeInscription> {

    @Mapping(source = "next.id", target = "nextId")
    @Mapping(source = "previous.id", target = "previousId")
    @Mapping(source = "statusEtape.id", target = "statusEtapeId")
    @Mapping(source = "etablissement.id", target = "etablissementId")
    EtapeInscriptionDTO toDto(EtapeInscription etapeInscription);

    @Mapping(source = "nextId", target = "next")
    @Mapping(source = "previousId", target = "previous")
    @Mapping(source = "statusEtapeId", target = "statusEtape")
    @Mapping(source = "etablissementId", target = "etablissement")
    EtapeInscription toEntity(EtapeInscriptionDTO etapeInscriptionDTO);

    default EtapeInscription fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtapeInscription etapeInscription = new EtapeInscription();
        etapeInscription.setId(id);
        return etapeInscription;
    }
}
