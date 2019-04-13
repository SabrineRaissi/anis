package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.StatusEtapeInscriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing StatusEtapeInscription.
 */
public interface StatusEtapeInscriptionService {

    /**
     * Save a statusEtapeInscription.
     *
     * @param statusEtapeInscriptionDTO the entity to save
     * @return the persisted entity
     */
    StatusEtapeInscriptionDTO save(StatusEtapeInscriptionDTO statusEtapeInscriptionDTO);

    /**
     * Get all the statusEtapeInscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StatusEtapeInscriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" statusEtapeInscription.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StatusEtapeInscriptionDTO> findOne(Long id);

    /**
     * Delete the "id" statusEtapeInscription.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
