package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PasswordResetTokenService;
import com.mycompany.myapp.domain.PasswordResetToken;
import com.mycompany.myapp.repository.PasswordResetTokenRepository;
import com.mycompany.myapp.service.dto.PasswordResetTokenDTO;
import com.mycompany.myapp.service.mapper.PasswordResetTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PasswordResetToken.
 */
@Service
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final Logger log = LoggerFactory.getLogger(PasswordResetTokenServiceImpl.class);

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final PasswordResetTokenMapper passwordResetTokenMapper;

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository, PasswordResetTokenMapper passwordResetTokenMapper) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordResetTokenMapper = passwordResetTokenMapper;
    }

    /**
     * Save a passwordResetToken.
     *
     * @param passwordResetTokenDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PasswordResetTokenDTO save(PasswordResetTokenDTO passwordResetTokenDTO) {
        log.debug("Request to save PasswordResetToken : {}", passwordResetTokenDTO);
        PasswordResetToken passwordResetToken = passwordResetTokenMapper.toEntity(passwordResetTokenDTO);
        passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
        return passwordResetTokenMapper.toDto(passwordResetToken);
    }

    /**
     * Get all the passwordResetTokens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PasswordResetTokenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PasswordResetTokens");
        return passwordResetTokenRepository.findAll(pageable)
            .map(passwordResetTokenMapper::toDto);
    }


    /**
     * Get one passwordResetToken by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PasswordResetTokenDTO> findOne(Long id) {
        log.debug("Request to get PasswordResetToken : {}", id);
        return passwordResetTokenRepository.findById(id)
            .map(passwordResetTokenMapper::toDto);
    }

    /**
     * Delete the passwordResetToken by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PasswordResetToken : {}", id);
        passwordResetTokenRepository.deleteById(id);
    }
}
