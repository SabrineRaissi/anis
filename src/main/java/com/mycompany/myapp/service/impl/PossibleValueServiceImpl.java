package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PossibleValueService;
import com.mycompany.myapp.domain.PossibleValue;
import com.mycompany.myapp.repository.PossibleValueRepository;
import com.mycompany.myapp.service.dto.PossibleValueDTO;
import com.mycompany.myapp.service.mapper.PossibleValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PossibleValue.
 */
@Service
@Transactional
public class PossibleValueServiceImpl implements PossibleValueService {

    private final Logger log = LoggerFactory.getLogger(PossibleValueServiceImpl.class);

    private final PossibleValueRepository possibleValueRepository;

    private final PossibleValueMapper possibleValueMapper;

    public PossibleValueServiceImpl(PossibleValueRepository possibleValueRepository, PossibleValueMapper possibleValueMapper) {
        this.possibleValueRepository = possibleValueRepository;
        this.possibleValueMapper = possibleValueMapper;
    }

    /**
     * Save a possibleValue.
     *
     * @param possibleValueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PossibleValueDTO save(PossibleValueDTO possibleValueDTO) {
        log.debug("Request to save PossibleValue : {}", possibleValueDTO);
        PossibleValue possibleValue = possibleValueMapper.toEntity(possibleValueDTO);
        possibleValue = possibleValueRepository.save(possibleValue);
        return possibleValueMapper.toDto(possibleValue);
    }

    /**
     * Get all the possibleValues.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PossibleValueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PossibleValues");
        return possibleValueRepository.findAll(pageable)
            .map(possibleValueMapper::toDto);
    }


    /**
     * Get one possibleValue by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PossibleValueDTO> findOne(Long id) {
        log.debug("Request to get PossibleValue : {}", id);
        return possibleValueRepository.findById(id)
            .map(possibleValueMapper::toDto);
    }

    /**
     * Delete the possibleValue by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PossibleValue : {}", id);
        possibleValueRepository.deleteById(id);
    }
}
