package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.StatusSessionService;
import com.mycompany.myapp.domain.StatusSession;
import com.mycompany.myapp.repository.StatusSessionRepository;
import com.mycompany.myapp.service.dto.StatusSessionDTO;
import com.mycompany.myapp.service.mapper.StatusSessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing StatusSession.
 */
@Service
@Transactional
public class StatusSessionServiceImpl implements StatusSessionService {

    private final Logger log = LoggerFactory.getLogger(StatusSessionServiceImpl.class);

    private final StatusSessionRepository statusSessionRepository;

    private final StatusSessionMapper statusSessionMapper;

    public StatusSessionServiceImpl(StatusSessionRepository statusSessionRepository, StatusSessionMapper statusSessionMapper) {
        this.statusSessionRepository = statusSessionRepository;
        this.statusSessionMapper = statusSessionMapper;
    }

    /**
     * Save a statusSession.
     *
     * @param statusSessionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StatusSessionDTO save(StatusSessionDTO statusSessionDTO) {
        log.debug("Request to save StatusSession : {}", statusSessionDTO);
        StatusSession statusSession = statusSessionMapper.toEntity(statusSessionDTO);
        statusSession = statusSessionRepository.save(statusSession);
        return statusSessionMapper.toDto(statusSession);
    }

    /**
     * Get all the statusSessions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StatusSessionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StatusSessions");
        return statusSessionRepository.findAll(pageable)
            .map(statusSessionMapper::toDto);
    }


    /**
     * Get one statusSession by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StatusSessionDTO> findOne(Long id) {
        log.debug("Request to get StatusSession : {}", id);
        return statusSessionRepository.findById(id)
            .map(statusSessionMapper::toDto);
    }

    /**
     * Delete the statusSession by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StatusSession : {}", id);
        statusSessionRepository.deleteById(id);
    }
}
