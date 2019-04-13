package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.StatusEtapeInscriptionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.StatusEtapeInscriptionDTO;
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
 * REST controller for managing StatusEtapeInscription.
 */
@RestController
@RequestMapping("/api")
public class StatusEtapeInscriptionResource {

    private final Logger log = LoggerFactory.getLogger(StatusEtapeInscriptionResource.class);

    private static final String ENTITY_NAME = "statusEtapeInscription";

    private final StatusEtapeInscriptionService statusEtapeInscriptionService;

    public StatusEtapeInscriptionResource(StatusEtapeInscriptionService statusEtapeInscriptionService) {
        this.statusEtapeInscriptionService = statusEtapeInscriptionService;
    }

    /**
     * POST  /status-etape-inscriptions : Create a new statusEtapeInscription.
     *
     * @param statusEtapeInscriptionDTO the statusEtapeInscriptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statusEtapeInscriptionDTO, or with status 400 (Bad Request) if the statusEtapeInscription has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/status-etape-inscriptions")
    public ResponseEntity<StatusEtapeInscriptionDTO> createStatusEtapeInscription(@RequestBody StatusEtapeInscriptionDTO statusEtapeInscriptionDTO) throws URISyntaxException {
        log.debug("REST request to save StatusEtapeInscription : {}", statusEtapeInscriptionDTO);
        if (statusEtapeInscriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new statusEtapeInscription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatusEtapeInscriptionDTO result = statusEtapeInscriptionService.save(statusEtapeInscriptionDTO);
        return ResponseEntity.created(new URI("/api/status-etape-inscriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /status-etape-inscriptions : Updates an existing statusEtapeInscription.
     *
     * @param statusEtapeInscriptionDTO the statusEtapeInscriptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statusEtapeInscriptionDTO,
     * or with status 400 (Bad Request) if the statusEtapeInscriptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the statusEtapeInscriptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/status-etape-inscriptions")
    public ResponseEntity<StatusEtapeInscriptionDTO> updateStatusEtapeInscription(@RequestBody StatusEtapeInscriptionDTO statusEtapeInscriptionDTO) throws URISyntaxException {
        log.debug("REST request to update StatusEtapeInscription : {}", statusEtapeInscriptionDTO);
        if (statusEtapeInscriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatusEtapeInscriptionDTO result = statusEtapeInscriptionService.save(statusEtapeInscriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statusEtapeInscriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /status-etape-inscriptions : get all the statusEtapeInscriptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of statusEtapeInscriptions in body
     */
    @GetMapping("/status-etape-inscriptions")
    public ResponseEntity<List<StatusEtapeInscriptionDTO>> getAllStatusEtapeInscriptions(Pageable pageable) {
        log.debug("REST request to get a page of StatusEtapeInscriptions");
        Page<StatusEtapeInscriptionDTO> page = statusEtapeInscriptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/status-etape-inscriptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /status-etape-inscriptions/:id : get the "id" statusEtapeInscription.
     *
     * @param id the id of the statusEtapeInscriptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statusEtapeInscriptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/status-etape-inscriptions/{id}")
    public ResponseEntity<StatusEtapeInscriptionDTO> getStatusEtapeInscription(@PathVariable Long id) {
        log.debug("REST request to get StatusEtapeInscription : {}", id);
        Optional<StatusEtapeInscriptionDTO> statusEtapeInscriptionDTO = statusEtapeInscriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statusEtapeInscriptionDTO);
    }

    /**
     * DELETE  /status-etape-inscriptions/:id : delete the "id" statusEtapeInscription.
     *
     * @param id the id of the statusEtapeInscriptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/status-etape-inscriptions/{id}")
    public ResponseEntity<Void> deleteStatusEtapeInscription(@PathVariable Long id) {
        log.debug("REST request to delete StatusEtapeInscription : {}", id);
        statusEtapeInscriptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
