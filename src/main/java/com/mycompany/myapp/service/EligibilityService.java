package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EligibilityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Eligibility.
 */
public interface EligibilityService {

    /**
     * Save a eligibility.
     *
     * @param eligibilityDTO the entity to save
     * @return the persisted entity
     */
    EligibilityDTO save(EligibilityDTO eligibilityDTO);

    /**
     * Get all the eligibilities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EligibilityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" eligibility.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EligibilityDTO> findOne(Long id);

    /**
     * Delete the "id" eligibility.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
