package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.StatusInscriptionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.StatusInscriptionDTO;
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
 * REST controller for managing StatusInscription.
 */
@RestController
@RequestMapping("/api")
public class StatusInscriptionResource {

    private final Logger log = LoggerFactory.getLogger(StatusInscriptionResource.class);

    private static final String ENTITY_NAME = "statusInscription";

    private final StatusInscriptionService statusInscriptionService;

    public StatusInscriptionResource(StatusInscriptionService statusInscriptionService) {
        this.statusInscriptionService = statusInscriptionService;
    }

    /**
     * POST  /status-inscriptions : Create a new statusInscription.
     *
     * @param statusInscriptionDTO the statusInscriptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statusInscriptionDTO, or with status 400 (Bad Request) if the statusInscription has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/status-inscriptions")
    public ResponseEntity<StatusInscriptionDTO> createStatusInscription(@RequestBody StatusInscriptionDTO statusInscriptionDTO) throws URISyntaxException {
        log.debug("REST request to save StatusInscription : {}", statusInscriptionDTO);
        if (statusInscriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new statusInscription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatusInscriptionDTO result = statusInscriptionService.save(statusInscriptionDTO);
        return ResponseEntity.created(new URI("/api/status-inscriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /status-inscriptions : Updates an existing statusInscription.
     *
     * @param statusInscriptionDTO the statusInscriptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statusInscriptionDTO,
     * or with status 400 (Bad Request) if the statusInscriptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the statusInscriptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/status-inscriptions")
    public ResponseEntity<StatusInscriptionDTO> updateStatusInscription(@RequestBody StatusInscriptionDTO statusInscriptionDTO) throws URISyntaxException {
        log.debug("REST request to update StatusInscription : {}", statusInscriptionDTO);
        if (statusInscriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatusInscriptionDTO result = statusInscriptionService.save(statusInscriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statusInscriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /status-inscriptions : get all the statusInscriptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of statusInscriptions in body
     */
    @GetMapping("/status-inscriptions")
    public ResponseEntity<List<StatusInscriptionDTO>> getAllStatusInscriptions(Pageable pageable) {
        log.debug("REST request to get a page of StatusInscriptions");
        Page<StatusInscriptionDTO> page = statusInscriptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/status-inscriptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /status-inscriptions/:id : get the "id" statusInscription.
     *
     * @param id the id of the statusInscriptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statusInscriptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/status-inscriptions/{id}")
    public ResponseEntity<StatusInscriptionDTO> getStatusInscription(@PathVariable Long id) {
        log.debug("REST request to get StatusInscription : {}", id);
        Optional<StatusInscriptionDTO> statusInscriptionDTO = statusInscriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statusInscriptionDTO);
    }

    /**
     * DELETE  /status-inscriptions/:id : delete the "id" statusInscription.
     *
     * @param id the id of the statusInscriptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/status-inscriptions/{id}")
    public ResponseEntity<Void> deleteStatusInscription(@PathVariable Long id) {
        log.debug("REST request to delete StatusInscription : {}", id);
        statusInscriptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
