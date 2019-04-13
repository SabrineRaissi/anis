package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.InscriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Inscription and its DTO InscriptionDTO.
 */
@Mapper(componentModel = "spring", uses = {DoctorantMapper.class, TheseMapper.class, StatusInscriptionMapper.class, EtapeInscriptionMapper.class})
public interface InscriptionMapper extends EntityMapper<InscriptionDTO, Inscription> {

    @Mapping(source = "doctorant.id", target = "doctorantId")
    @Mapping(source = "these.id", target = "theseId")
    @Mapping(source = "statusInscription.id", target = "statusInscriptionId")
    @Mapping(source = "nextStep.id", target = "nextStepId")
    InscriptionDTO toDto(Inscription inscription);

    @Mapping(source = "doctorantId", target = "doctorant")
    @Mapping(source = "theseId", target = "these")
    @Mapping(source = "statusInscriptionId", target = "statusInscription")
    @Mapping(source = "nextStepId", target = "nextStep")
    Inscription toEntity(InscriptionDTO inscriptionDTO);

    default Inscription fromId(Long id) {
        if (id == null) {
            return null;
        }
        Inscription inscription = new Inscription();
        inscription.setId(id);
        return inscription;
    }
}
