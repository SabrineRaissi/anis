package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PasswordResetTokenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PasswordResetToken.
 */
public interface PasswordResetTokenService {

    /**
     * Save a passwordResetToken.
     *
     * @param passwordResetTokenDTO the entity to save
     * @return the persisted entity
     */
    PasswordResetTokenDTO save(PasswordResetTokenDTO passwordResetTokenDTO);

    /**
     * Get all the passwordResetTokens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PasswordResetTokenDTO> findAll(Pageable pageable);


    /**
     * Get the "id" passwordResetToken.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PasswordResetTokenDTO> findOne(Long id);

    /**
     * Delete the "id" passwordResetToken.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
