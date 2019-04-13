package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TheseService;
import com.mycompany.myapp.domain.These;
import com.mycompany.myapp.repository.TheseRepository;
import com.mycompany.myapp.service.dto.TheseDTO;
import com.mycompany.myapp.service.mapper.TheseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing These.
 */
@Service
@Transactional
public class TheseServiceImpl implements TheseService {

    private final Logger log = LoggerFactory.getLogger(TheseServiceImpl.class);

    private final TheseRepository theseRepository;

    private final TheseMapper theseMapper;

    public TheseServiceImpl(TheseRepository theseRepository, TheseMapper theseMapper) {
        this.theseRepository = theseRepository;
        this.theseMapper = theseMapper;
    }

    /**
     * Save a these.
     *
     * @param theseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TheseDTO save(TheseDTO theseDTO) {
        log.debug("Request to save These : {}", theseDTO);
        These these = theseMapper.toEntity(theseDTO);
        these = theseRepository.save(these);
        return theseMapper.toDto(these);
    }

    /**
     * Get all the these.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TheseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all These");
        return theseRepository.findAll(pageable)
            .map(theseMapper::toDto);
    }


    /**
     * Get one these by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TheseDTO> findOne(Long id) {
        log.debug("Request to get These : {}", id);
        return theseRepository.findById(id)
            .map(theseMapper::toDto);
    }

    /**
     * Delete the these by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete These : {}", id);
        theseRepository.deleteById(id);
    }
}
