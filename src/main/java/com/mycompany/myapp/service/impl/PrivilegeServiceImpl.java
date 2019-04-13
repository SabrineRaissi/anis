package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PrivilegeService;
import com.mycompany.myapp.domain.Privilege;
import com.mycompany.myapp.repository.PrivilegeRepository;
import com.mycompany.myapp.service.dto.PrivilegeDTO;
import com.mycompany.myapp.service.mapper.PrivilegeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Privilege.
 */
@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

    private final Logger log = LoggerFactory.getLogger(PrivilegeServiceImpl.class);

    private final PrivilegeRepository privilegeRepository;

    private final PrivilegeMapper privilegeMapper;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository, PrivilegeMapper privilegeMapper) {
        this.privilegeRepository = privilegeRepository;
        this.privilegeMapper = privilegeMapper;
    }

    /**
     * Save a privilege.
     *
     * @param privilegeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PrivilegeDTO save(PrivilegeDTO privilegeDTO) {
        log.debug("Request to save Privilege : {}", privilegeDTO);
        Privilege privilege = privilegeMapper.toEntity(privilegeDTO);
        privilege = privilegeRepository.save(privilege);
        return privilegeMapper.toDto(privilege);
    }

    /**
     * Get all the privileges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PrivilegeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Privileges");
        return privilegeRepository.findAll(pageable)
            .map(privilegeMapper::toDto);
    }


    /**
     * Get one privilege by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PrivilegeDTO> findOne(Long id) {
        log.debug("Request to get Privilege : {}", id);
        return privilegeRepository.findById(id)
            .map(privilegeMapper::toDto);
    }

    /**
     * Delete the privilege by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Privilege : {}", id);
        privilegeRepository.deleteById(id);
    }
}
