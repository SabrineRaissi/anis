package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.StatusInscriptionService;
import com.mycompany.myapp.domain.StatusInscription;
import com.mycompany.myapp.repository.StatusInscriptionRepository;
import com.mycompany.myapp.service.dto.StatusInscriptionDTO;
import com.mycompany.myapp.service.mapper.StatusInscriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing StatusInscription.
 */
@Service
@Transactional
public class StatusInscriptionServiceImpl implements StatusInscriptionService {

    private final Logger log = LoggerFactory.getLogger(StatusInscriptionServiceImpl.class);

    private final StatusInscriptionRepository statusInscriptionRepository;

    private final StatusInscriptionMapper statusInscriptionMapper;

    public StatusInscriptionServiceImpl(StatusInscriptionRepository statusInscriptionRepository, StatusInscriptionMapper statusInscriptionMapper) {
        this.statusInscriptionRepository = statusInscriptionRepository;
        this.statusInscriptionMapper = statusInscriptionMapper;
    }

    /**
     * Save a statusInscription.
     *
     * @param statusInscriptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StatusInscriptionDTO save(StatusInscriptionDTO statusInscriptionDTO) {
        log.debug("Request to save StatusInscription : {}", statusInscriptionDTO);
        StatusInscription statusInscription = statusInscriptionMapper.toEntity(statusInscriptionDTO);
        statusInscription = statusInscriptionRepository.save(statusInscription);
        return statusInscriptionMapper.toDto(statusInscription);
    }

    /**
     * Get all the statusInscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StatusInscriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StatusInscriptions");
        return statusInscriptionRepository.findAll(pageable)
            .map(statusInscriptionMapper::toDto);
    }


    /**
     * Get one statusInscription by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StatusInscriptionDTO> findOne(Long id) {
        log.debug("Request to get StatusInscription : {}", id);
        return statusInscriptionRepository.findById(id)
            .map(statusInscriptionMapper::toDto);
    }

    /**
     * Delete the statusInscription by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StatusInscription : {}", id);
        statusInscriptionRepository.deleteById(id);
    }
}
