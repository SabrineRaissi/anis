package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DoctorantService;
import com.mycompany.myapp.domain.Doctorant;
import com.mycompany.myapp.repository.DoctorantRepository;
import com.mycompany.myapp.service.dto.DoctorantDTO;
import com.mycompany.myapp.service.mapper.DoctorantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Doctorant.
 */
@Service
@Transactional
public class DoctorantServiceImpl implements DoctorantService {

    private final Logger log = LoggerFactory.getLogger(DoctorantServiceImpl.class);

    private final DoctorantRepository doctorantRepository;

    private final DoctorantMapper doctorantMapper;

    public DoctorantServiceImpl(DoctorantRepository doctorantRepository, DoctorantMapper doctorantMapper) {
        this.doctorantRepository = doctorantRepository;
        this.doctorantMapper = doctorantMapper;
    }

    /**
     * Save a doctorant.
     *
     * @param doctorantDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DoctorantDTO save(DoctorantDTO doctorantDTO) {
        log.debug("Request to save Doctorant : {}", doctorantDTO);
        Doctorant doctorant = doctorantMapper.toEntity(doctorantDTO);
        doctorant = doctorantRepository.save(doctorant);
        return doctorantMapper.toDto(doctorant);
    }

    /**
     * Get all the doctorants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DoctorantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Doctorants");
        return doctorantRepository.findAll(pageable)
            .map(doctorantMapper::toDto);
    }


    /**
     * Get one doctorant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorantDTO> findOne(Long id) {
        log.debug("Request to get Doctorant : {}", id);
        return doctorantRepository.findById(id)
            .map(doctorantMapper::toDto);
    }

    /**
     * Delete the doctorant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Doctorant : {}", id);
        doctorantRepository.deleteById(id);
    }
}
