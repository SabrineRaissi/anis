package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PossibleValueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PossibleValue.
 */
public interface PossibleValueService {

    /**
     * Save a possibleValue.
     *
     * @param possibleValueDTO the entity to save
     * @return the persisted entity
     */
    PossibleValueDTO save(PossibleValueDTO possibleValueDTO);

    /**
     * Get all the possibleValues.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PossibleValueDTO> findAll(Pageable pageable);


    /**
     * Get the "id" possibleValue.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PossibleValueDTO> findOne(Long id);

    /**
     * Delete the "id" possibleValue.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
