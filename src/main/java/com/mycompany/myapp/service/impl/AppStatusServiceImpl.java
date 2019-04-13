package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AppStatusService;
import com.mycompany.myapp.domain.AppStatus;
import com.mycompany.myapp.repository.AppStatusRepository;
import com.mycompany.myapp.service.dto.AppStatusDTO;
import com.mycompany.myapp.service.mapper.AppStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AppStatus.
 */
@Service
@Transactional
public class AppStatusServiceImpl implements AppStatusService {

    private final Logger log = LoggerFactory.getLogger(AppStatusServiceImpl.class);

    private final AppStatusRepository appStatusRepository;

    private final AppStatusMapper appStatusMapper;

    public AppStatusServiceImpl(AppStatusRepository appStatusRepository, AppStatusMapper appStatusMapper) {
        this.appStatusRepository = appStatusRepository;
        this.appStatusMapper = appStatusMapper;
    }

    /**
     * Save a appStatus.
     *
     * @param appStatusDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AppStatusDTO save(AppStatusDTO appStatusDTO) {
        log.debug("Request to save AppStatus : {}", appStatusDTO);
        AppStatus appStatus = appStatusMapper.toEntity(appStatusDTO);
        appStatus = appStatusRepository.save(appStatus);
        return appStatusMapper.toDto(appStatus);
    }

    /**
     * Get all the appStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AppStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppStatuses");
        return appStatusRepository.findAll(pageable)
            .map(appStatusMapper::toDto);
    }


    /**
     * Get one appStatus by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AppStatusDTO> findOne(Long id) {
        log.debug("Request to get AppStatus : {}", id);
        return appStatusRepository.findById(id)
            .map(appStatusMapper::toDto);
    }

    /**
     * Delete the appStatus by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppStatus : {}", id);
        appStatusRepository.deleteById(id);
    }
}
