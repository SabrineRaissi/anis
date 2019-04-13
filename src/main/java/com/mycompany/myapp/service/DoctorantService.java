package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DoctorantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Doctorant.
 */
public interface DoctorantService {

    /**
     * Save a doctorant.
     *
     * @param doctorantDTO the entity to save
     * @return the persisted entity
     */
    DoctorantDTO save(DoctorantDTO doctorantDTO);

    /**
     * Get all the doctorants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DoctorantDTO> findAll(Pageable pageable);


    /**
     * Get the "id" doctorant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DoctorantDTO> findOne(Long id);

    /**
     * Delete the "id" doctorant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
