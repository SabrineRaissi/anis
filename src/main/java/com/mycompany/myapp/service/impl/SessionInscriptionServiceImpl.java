package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SessionInscriptionService;
import com.mycompany.myapp.domain.SessionInscription;
import com.mycompany.myapp.repository.SessionInscriptionRepository;
import com.mycompany.myapp.service.dto.SessionInscriptionDTO;
import com.mycompany.myapp.service.mapper.SessionInscriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SessionInscription.
 */
@Service
@Transactional
public class SessionInscriptionServiceImpl implements SessionInscriptionService {

    private final Logger log = LoggerFactory.getLogger(SessionInscriptionServiceImpl.class);

    private final SessionInscriptionRepository sessionInscriptionRepository;

    private final SessionInscriptionMapper sessionInscriptionMapper;

    public SessionInscriptionServiceImpl(SessionInscriptionRepository sessionInscriptionRepository, SessionInscriptionMapper sessionInscriptionMapper) {
        this.sessionInscriptionRepository = sessionInscriptionRepository;
        this.sessionInscriptionMapper = sessionInscriptionMapper;
    }

    /**
     * Save a sessionInscription.
     *
     * @param sessionInscriptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SessionInscriptionDTO save(SessionInscriptionDTO sessionInscriptionDTO) {
        log.debug("Request to save SessionInscription : {}", sessionInscriptionDTO);
        SessionInscription sessionInscription = sessionInscriptionMapper.toEntity(sessionInscriptionDTO);
        sessionInscription = sessionInscriptionRepository.save(sessionInscription);
        return sessionInscriptionMapper.toDto(sessionInscription);
    }

    /**
     * Get all the sessionInscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SessionInscriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SessionInscriptions");
        return sessionInscriptionRepository.findAll(pageable)
            .map(sessionInscriptionMapper::toDto);
    }


    /**
     * Get one sessionInscription by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SessionInscriptionDTO> findOne(Long id) {
        log.debug("Request to get SessionInscription : {}", id);
        return sessionInscriptionRepository.findById(id)
            .map(sessionInscriptionMapper::toDto);
    }

    /**
     * Delete the sessionInscription by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SessionInscription : {}", id);
        sessionInscriptionRepository.deleteById(id);
    }
}
