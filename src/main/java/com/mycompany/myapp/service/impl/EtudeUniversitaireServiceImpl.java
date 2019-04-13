package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EtudeUniversitaireService;
import com.mycompany.myapp.domain.EtudeUniversitaire;
import com.mycompany.myapp.repository.EtudeUniversitaireRepository;
import com.mycompany.myapp.service.dto.EtudeUniversitaireDTO;
import com.mycompany.myapp.service.mapper.EtudeUniversitaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EtudeUniversitaire.
 */
@Service
@Transactional
public class EtudeUniversitaireServiceImpl implements EtudeUniversitaireService {

    private final Logger log = LoggerFactory.getLogger(EtudeUniversitaireServiceImpl.class);

    private final EtudeUniversitaireRepository etudeUniversitaireRepository;

    private final EtudeUniversitaireMapper etudeUniversitaireMapper;

    public EtudeUniversitaireServiceImpl(EtudeUniversitaireRepository etudeUniversitaireRepository, EtudeUniversitaireMapper etudeUniversitaireMapper) {
        this.etudeUniversitaireRepository = etudeUniversitaireRepository;
        this.etudeUniversitaireMapper = etudeUniversitaireMapper;
    }

    /**
     * Save a etudeUniversitaire.
     *
     * @param etudeUniversitaireDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EtudeUniversitaireDTO save(EtudeUniversitaireDTO etudeUniversitaireDTO) {
        log.debug("Request to save EtudeUniversitaire : {}", etudeUniversitaireDTO);
        EtudeUniversitaire etudeUniversitaire = etudeUniversitaireMapper.toEntity(etudeUniversitaireDTO);
        etudeUniversitaire = etudeUniversitaireRepository.save(etudeUniversitaire);
        return etudeUniversitaireMapper.toDto(etudeUniversitaire);
    }

    /**
     * Get all the etudeUniversitaires.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtudeUniversitaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtudeUniversitaires");
        return etudeUniversitaireRepository.findAll(pageable)
            .map(etudeUniversitaireMapper::toDto);
    }


    /**
     * Get one etudeUniversitaire by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtudeUniversitaireDTO> findOne(Long id) {
        log.debug("Request to get EtudeUniversitaire : {}", id);
        return etudeUniversitaireRepository.findById(id)
            .map(etudeUniversitaireMapper::toDto);
    }

    /**
     * Delete the etudeUniversitaire by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtudeUniversitaire : {}", id);
        etudeUniversitaireRepository.deleteById(id);
    }
}
