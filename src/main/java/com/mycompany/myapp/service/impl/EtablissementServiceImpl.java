package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EtablissementService;
import com.mycompany.myapp.domain.Etablissement;
import com.mycompany.myapp.repository.EtablissementRepository;
import com.mycompany.myapp.service.dto.EtablissementDTO;
import com.mycompany.myapp.service.mapper.EtablissementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Etablissement.
 */
@Service
@Transactional
public class EtablissementServiceImpl implements EtablissementService {

    private final Logger log = LoggerFactory.getLogger(EtablissementServiceImpl.class);

    private final EtablissementRepository etablissementRepository;

    private final EtablissementMapper etablissementMapper;

    public EtablissementServiceImpl(EtablissementRepository etablissementRepository, EtablissementMapper etablissementMapper) {
        this.etablissementRepository = etablissementRepository;
        this.etablissementMapper = etablissementMapper;
    }

    /**
     * Save a etablissement.
     *
     * @param etablissementDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EtablissementDTO save(EtablissementDTO etablissementDTO) {
        log.debug("Request to save Etablissement : {}", etablissementDTO);
        Etablissement etablissement = etablissementMapper.toEntity(etablissementDTO);
        etablissement = etablissementRepository.save(etablissement);
        return etablissementMapper.toDto(etablissement);
    }

    /**
     * Get all the etablissements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtablissementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Etablissements");
        return etablissementRepository.findAll(pageable)
            .map(etablissementMapper::toDto);
    }


    /**
     * Get one etablissement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtablissementDTO> findOne(Long id) {
        log.debug("Request to get Etablissement : {}", id);
        return etablissementRepository.findById(id)
            .map(etablissementMapper::toDto);
    }

    /**
     * Delete the etablissement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Etablissement : {}", id);
        etablissementRepository.deleteById(id);
    }
}
