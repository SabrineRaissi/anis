package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LastThesesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LastTheses.
 */
public interface LastThesesService {

    /**
     * Save a lastTheses.
     *
     * @param lastThesesDTO the entity to save
     * @return the persisted entity
     */
    LastThesesDTO save(LastThesesDTO lastThesesDTO);

    /**
     * Get all the lastTheses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LastThesesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lastTheses.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LastThesesDTO> findOne(Long id);

    /**
     * Delete the "id" lastTheses.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
