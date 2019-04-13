package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.PrivilegeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.PrivilegeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Privilege.
 */
@RestController
@RequestMapping("/api")
public class PrivilegeResource {

    private final Logger log = LoggerFactory.getLogger(PrivilegeResource.class);

    private static final String ENTITY_NAME = "privilege";

    private final PrivilegeService privilegeService;

    public PrivilegeResource(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    /**
     * POST  /privileges : Create a new privilege.
     *
     * @param privilegeDTO the privilegeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new privilegeDTO, or with status 400 (Bad Request) if the privilege has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/privileges")
    public ResponseEntity<PrivilegeDTO> createPrivilege(@RequestBody PrivilegeDTO privilegeDTO) throws URISyntaxException {
        log.debug("REST request to save Privilege : {}", privilegeDTO);
        if (privilegeDTO.getId() != null) {
            throw new BadRequestAlertException("A new privilege cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrivilegeDTO result = privilegeService.save(privilegeDTO);
        return ResponseEntity.created(new URI("/api/privileges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /privileges : Updates an existing privilege.
     *
     * @param privilegeDTO the privilegeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated privilegeDTO,
     * or with status 400 (Bad Request) if the privilegeDTO is not valid,
     * or with status 500 (Internal Server Error) if the privilegeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/privileges")
    public ResponseEntity<PrivilegeDTO> updatePrivilege(@RequestBody PrivilegeDTO privilegeDTO) throws URISyntaxException {
        log.debug("REST request to update Privilege : {}", privilegeDTO);
        if (privilegeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrivilegeDTO result = privilegeService.save(privilegeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, privilegeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /privileges : get all the privileges.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of privileges in body
     */
    @GetMapping("/privileges")
    public ResponseEntity<List<PrivilegeDTO>> getAllPrivileges(Pageable pageable) {
        log.debug("REST request to get a page of Privileges");
        Page<PrivilegeDTO> page = privilegeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/privileges");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /privileges/:id : get the "id" privilege.
     *
     * @param id the id of the privilegeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the privilegeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/privileges/{id}")
    public ResponseEntity<PrivilegeDTO> getPrivilege(@PathVariable Long id) {
        log.debug("REST request to get Privilege : {}", id);
        Optional<PrivilegeDTO> privilegeDTO = privilegeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(privilegeDTO);
    }

    /**
     * DELETE  /privileges/:id : delete the "id" privilege.
     *
     * @param id the id of the privilegeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/privileges/{id}")
    public ResponseEntity<Void> deletePrivilege(@PathVariable Long id) {
        log.debug("REST request to delete Privilege : {}", id);
        privilegeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
