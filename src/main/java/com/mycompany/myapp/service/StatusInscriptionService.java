package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.StatusInscriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing StatusInscription.
 */
public interface StatusInscriptionService {

    /**
     * Save a statusInscription.
     *
     * @param statusInscriptionDTO the entity to save
     * @return the persisted entity
     */
    StatusInscriptionDTO save(StatusInscriptionDTO statusInscriptionDTO);

    /**
     * Get all the statusInscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StatusInscriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" statusInscription.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StatusInscriptionDTO> findOne(Long id);

    /**
     * Delete the "id" statusInscription.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
