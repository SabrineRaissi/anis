package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TheseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing These.
 */
public interface TheseService {

    /**
     * Save a these.
     *
     * @param theseDTO the entity to save
     * @return the persisted entity
     */
    TheseDTO save(TheseDTO theseDTO);

    /**
     * Get all the these.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TheseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" these.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TheseDTO> findOne(Long id);

    /**
     * Delete the "id" these.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
