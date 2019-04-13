package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.SessionInscriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SessionInscription.
 */
public interface SessionInscriptionService {

    /**
     * Save a sessionInscription.
     *
     * @param sessionInscriptionDTO the entity to save
     * @return the persisted entity
     */
    SessionInscriptionDTO save(SessionInscriptionDTO sessionInscriptionDTO);

    /**
     * Get all the sessionInscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SessionInscriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sessionInscription.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SessionInscriptionDTO> findOne(Long id);

    /**
     * Delete the "id" sessionInscription.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
