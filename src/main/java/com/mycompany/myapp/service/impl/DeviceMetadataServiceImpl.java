package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DeviceMetadataService;
import com.mycompany.myapp.domain.DeviceMetadata;
import com.mycompany.myapp.repository.DeviceMetadataRepository;
import com.mycompany.myapp.service.dto.DeviceMetadataDTO;
import com.mycompany.myapp.service.mapper.DeviceMetadataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DeviceMetadata.
 */
@Service
@Transactional
public class DeviceMetadataServiceImpl implements DeviceMetadataService {

    private final Logger log = LoggerFactory.getLogger(DeviceMetadataServiceImpl.class);

    private final DeviceMetadataRepository deviceMetadataRepository;

    private final DeviceMetadataMapper deviceMetadataMapper;

    public DeviceMetadataServiceImpl(DeviceMetadataRepository deviceMetadataRepository, DeviceMetadataMapper deviceMetadataMapper) {
        this.deviceMetadataRepository = deviceMetadataRepository;
        this.deviceMetadataMapper = deviceMetadataMapper;
    }

    /**
     * Save a deviceMetadata.
     *
     * @param deviceMetadataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DeviceMetadataDTO save(DeviceMetadataDTO deviceMetadataDTO) {
        log.debug("Request to save DeviceMetadata : {}", deviceMetadataDTO);
        DeviceMetadata deviceMetadata = deviceMetadataMapper.toEntity(deviceMetadataDTO);
        deviceMetadata = deviceMetadataRepository.save(deviceMetadata);
        return deviceMetadataMapper.toDto(deviceMetadata);
    }

    /**
     * Get all the deviceMetadata.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeviceMetadataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeviceMetadata");
        return deviceMetadataRepository.findAll(pageable)
            .map(deviceMetadataMapper::toDto);
    }


    /**
     * Get one deviceMetadata by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceMetadataDTO> findOne(Long id) {
        log.debug("Request to get DeviceMetadata : {}", id);
        return deviceMetadataRepository.findById(id)
            .map(deviceMetadataMapper::toDto);
    }

    /**
     * Delete the deviceMetadata by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeviceMetadata : {}", id);
        deviceMetadataRepository.deleteById(id);
    }
}
