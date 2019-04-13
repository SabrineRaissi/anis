package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EtapeInscriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EtapeInscription.
 */
public interface EtapeInscriptionService {

    /**
     * Save a etapeInscription.
     *
     * @param etapeInscriptionDTO the entity to save
     * @return the persisted entity
     */
    EtapeInscriptionDTO save(EtapeInscriptionDTO etapeInscriptionDTO);

    /**
     * Get all the etapeInscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EtapeInscriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" etapeInscription.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EtapeInscriptionDTO> findOne(Long id);

    /**
     * Delete the "id" etapeInscription.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
