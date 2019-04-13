package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DeviceMetadataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DeviceMetadata.
 */
public interface DeviceMetadataService {

    /**
     * Save a deviceMetadata.
     *
     * @param deviceMetadataDTO the entity to save
     * @return the persisted entity
     */
    DeviceMetadataDTO save(DeviceMetadataDTO deviceMetadataDTO);

    /**
     * Get all the deviceMetadata.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DeviceMetadataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" deviceMetadata.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DeviceMetadataDTO> findOne(Long id);

    /**
     * Delete the "id" deviceMetadata.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
