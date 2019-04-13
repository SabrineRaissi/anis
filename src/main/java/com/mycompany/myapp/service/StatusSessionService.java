package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.StatusSessionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing StatusSession.
 */
public interface StatusSessionService {

    /**
     * Save a statusSession.
     *
     * @param statusSessionDTO the entity to save
     * @return the persisted entity
     */
    StatusSessionDTO save(StatusSessionDTO statusSessionDTO);

    /**
     * Get all the statusSessions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StatusSessionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" statusSession.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StatusSessionDTO> findOne(Long id);

    /**
     * Delete the "id" statusSession.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
