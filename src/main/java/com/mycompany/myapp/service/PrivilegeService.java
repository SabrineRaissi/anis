package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PrivilegeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Privilege.
 */
public interface PrivilegeService {

    /**
     * Save a privilege.
     *
     * @param privilegeDTO the entity to save
     * @return the persisted entity
     */
    PrivilegeDTO save(PrivilegeDTO privilegeDTO);

    /**
     * Get all the privileges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PrivilegeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" privilege.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PrivilegeDTO> findOne(Long id);

    /**
     * Delete the "id" privilege.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
