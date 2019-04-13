package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.StatusEtapeInscriptionService;
import com.mycompany.myapp.domain.StatusEtapeInscription;
import com.mycompany.myapp.repository.StatusEtapeInscriptionRepository;
import com.mycompany.myapp.service.dto.StatusEtapeInscriptionDTO;
import com.mycompany.myapp.service.mapper.StatusEtapeInscriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing StatusEtapeInscription.
 */
@Service
@Transactional
public class StatusEtapeInscriptionServiceImpl implements StatusEtapeInscriptionService {

    private final Logger log = LoggerFactory.getLogger(StatusEtapeInscriptionServiceImpl.class);

    private final StatusEtapeInscriptionRepository statusEtapeInscriptionRepository;

    private final StatusEtapeInscriptionMapper statusEtapeInscriptionMapper;

    public StatusEtapeInscriptionServiceImpl(StatusEtapeInscriptionRepository statusEtapeInscriptionRepository, StatusEtapeInscriptionMapper statusEtapeInscriptionMapper) {
        this.statusEtapeInscriptionRepository = statusEtapeInscriptionRepository;
        this.statusEtapeInscriptionMapper = statusEtapeInscriptionMapper;
    }

    /**
     * Save a statusEtapeInscription.
     *
     * @param statusEtapeInscriptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StatusEtapeInscriptionDTO save(StatusEtapeInscriptionDTO statusEtapeInscriptionDTO) {
        log.debug("Request to save StatusEtapeInscription : {}", statusEtapeInscriptionDTO);
        StatusEtapeInscription statusEtapeInscription = statusEtapeInscriptionMapper.toEntity(statusEtapeInscriptionDTO);
        statusEtapeInscription = statusEtapeInscriptionRepository.save(statusEtapeInscription);
        return statusEtapeInscriptionMapper.toDto(statusEtapeInscription);
    }

    /**
     * Get all the statusEtapeInscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StatusEtapeInscriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StatusEtapeInscriptions");
        return statusEtapeInscriptionRepository.findAll(pageable)
            .map(statusEtapeInscriptionMapper::toDto);
    }


    /**
     * Get one statusEtapeInscription by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StatusEtapeInscriptionDTO> findOne(Long id) {
        log.debug("Request to get StatusEtapeInscription : {}", id);
        return statusEtapeInscriptionRepository.findById(id)
            .map(statusEtapeInscriptionMapper::toDto);
    }

    /**
     * Delete the statusEtapeInscription by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StatusEtapeInscription : {}", id);
        statusEtapeInscriptionRepository.deleteById(id);
    }
}
