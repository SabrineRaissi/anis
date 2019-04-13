package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EtablissementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Etablissement.
 */
public interface EtablissementService {

    /**
     * Save a etablissement.
     *
     * @param etablissementDTO the entity to save
     * @return the persisted entity
     */
    EtablissementDTO save(EtablissementDTO etablissementDTO);

    /**
     * Get all the etablissements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EtablissementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" etablissement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EtablissementDTO> findOne(Long id);

    /**
     * Delete the "id" etablissement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
