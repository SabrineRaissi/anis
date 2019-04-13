package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AppStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AppStatus.
 */
public interface AppStatusService {

    /**
     * Save a appStatus.
     *
     * @param appStatusDTO the entity to save
     * @return the persisted entity
     */
    AppStatusDTO save(AppStatusDTO appStatusDTO);

    /**
     * Get all the appStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AppStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" appStatus.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AppStatusDTO> findOne(Long id);

    /**
     * Delete the "id" appStatus.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
