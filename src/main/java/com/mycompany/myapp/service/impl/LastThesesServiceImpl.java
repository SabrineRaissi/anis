package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.LastThesesService;
import com.mycompany.myapp.domain.LastTheses;
import com.mycompany.myapp.repository.LastThesesRepository;
import com.mycompany.myapp.service.dto.LastThesesDTO;
import com.mycompany.myapp.service.mapper.LastThesesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LastTheses.
 */
@Service
@Transactional
public class LastThesesServiceImpl implements LastThesesService {

    private final Logger log = LoggerFactory.getLogger(LastThesesServiceImpl.class);

    private final LastThesesRepository lastThesesRepository;

    private final LastThesesMapper lastThesesMapper;

    public LastThesesServiceImpl(LastThesesRepository lastThesesRepository, LastThesesMapper lastThesesMapper) {
        this.lastThesesRepository = lastThesesRepository;
        this.lastThesesMapper = lastThesesMapper;
    }

    /**
     * Save a lastTheses.
     *
     * @param lastThesesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LastThesesDTO save(LastThesesDTO lastThesesDTO) {
        log.debug("Request to save LastTheses : {}", lastThesesDTO);
        LastTheses lastTheses = lastThesesMapper.toEntity(lastThesesDTO);
        lastTheses = lastThesesRepository.save(lastTheses);
        return lastThesesMapper.toDto(lastTheses);
    }

    /**
     * Get all the lastTheses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LastThesesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LastTheses");
        return lastThesesRepository.findAll(pageable)
            .map(lastThesesMapper::toDto);
    }


    /**
     * Get one lastTheses by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LastThesesDTO> findOne(Long id) {
        log.debug("Request to get LastTheses : {}", id);
        return lastThesesRepository.findById(id)
            .map(lastThesesMapper::toDto);
    }

    /**
     * Delete the lastTheses by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LastTheses : {}", id);
        lastThesesRepository.deleteById(id);
    }
}
