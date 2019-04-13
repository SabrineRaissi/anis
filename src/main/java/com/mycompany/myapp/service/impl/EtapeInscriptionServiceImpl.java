package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EtapeInscriptionService;
import com.mycompany.myapp.domain.EtapeInscription;
import com.mycompany.myapp.repository.EtapeInscriptionRepository;
import com.mycompany.myapp.service.dto.EtapeInscriptionDTO;
import com.mycompany.myapp.service.mapper.EtapeInscriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EtapeInscription.
 */
@Service
@Transactional
public class EtapeInscriptionServiceImpl implements EtapeInscriptionService {

    private final Logger log = LoggerFactory.getLogger(EtapeInscriptionServiceImpl.class);

    private final EtapeInscriptionRepository etapeInscriptionRepository;

    private final EtapeInscriptionMapper etapeInscriptionMapper;

    public EtapeInscriptionServiceImpl(EtapeInscriptionRepository etapeInscriptionRepository, EtapeInscriptionMapper etapeInscriptionMapper) {
        this.etapeInscriptionRepository = etapeInscriptionRepository;
        this.etapeInscriptionMapper = etapeInscriptionMapper;
    }

    /**
     * Save a etapeInscription.
     *
     * @param etapeInscriptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EtapeInscriptionDTO save(EtapeInscriptionDTO etapeInscriptionDTO) {
        log.debug("Request to save EtapeInscription : {}", etapeInscriptionDTO);
        EtapeInscription etapeInscription = etapeInscriptionMapper.toEntity(etapeInscriptionDTO);
        etapeInscription = etapeInscriptionRepository.save(etapeInscription);
        return etapeInscriptionMapper.toDto(etapeInscription);
    }

    /**
     * Get all the etapeInscriptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtapeInscriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtapeInscriptions");
        return etapeInscriptionRepository.findAll(pageable)
            .map(etapeInscriptionMapper::toDto);
    }


    /**
     * Get one etapeInscription by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtapeInscriptionDTO> findOne(Long id) {
        log.debug("Request to get EtapeInscription : {}", id);
        return etapeInscriptionRepository.findById(id)
            .map(etapeInscriptionMapper::toDto);
    }

    /**
     * Delete the etapeInscription by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtapeInscription : {}", id);
        etapeInscriptionRepository.deleteById(id);
    }
}
