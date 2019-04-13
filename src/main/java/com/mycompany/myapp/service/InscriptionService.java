package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.InscriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Inscription.
 */
public interface InscriptionService {

    /**
     * Save a inscription.
     *
     * @param inscriptionDTO the entity to save
     * @return the persisted entity
     */
    InscriptionDTO save(InscriptionDTO inscriptionDTO);

    /**
     * Get all the inscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<InscriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" inscription.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InscriptionDTO> findOne(Long id);

    /**
     * Delete the "id" inscription.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
