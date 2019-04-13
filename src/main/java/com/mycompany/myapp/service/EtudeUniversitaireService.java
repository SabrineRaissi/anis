package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EtudeUniversitaireDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EtudeUniversitaire.
 */
public interface EtudeUniversitaireService {

    /**
     * Save a etudeUniversitaire.
     *
     * @param etudeUniversitaireDTO the entity to save
     * @return the persisted entity
     */
    EtudeUniversitaireDTO save(EtudeUniversitaireDTO etudeUniversitaireDTO);

    /**
     * Get all the etudeUniversitaires.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EtudeUniversitaireDTO> findAll(Pageable pageable);


    /**
     * Get the "id" etudeUniversitaire.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EtudeUniversitaireDTO> findOne(Long id);

    /**
     * Delete the "id" etudeUniversitaire.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
