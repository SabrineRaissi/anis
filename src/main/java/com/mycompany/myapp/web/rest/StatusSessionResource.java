package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.StatusSessionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.StatusSessionDTO;
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
 * REST controller for managing StatusSession.
 */
@RestController
@RequestMapping("/api")
public class StatusSessionResource {

    private final Logger log = LoggerFactory.getLogger(StatusSessionResource.class);

    private static final String ENTITY_NAME = "statusSession";

    private final StatusSessionService statusSessionService;

    public StatusSessionResource(StatusSessionService statusSessionService) {
        this.statusSessionService = statusSessionService;
    }

    /**
     * POST  /status-sessions : Create a new statusSession.
     *
     * @param statusSessionDTO the statusSessionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statusSessionDTO, or with status 400 (Bad Request) if the statusSession has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/status-sessions")
    public ResponseEntity<StatusSessionDTO> createStatusSession(@RequestBody StatusSessionDTO statusSessionDTO) throws URISyntaxException {
        log.debug("REST request to save StatusSession : {}", statusSessionDTO);
        if (statusSessionDTO.getId() != null) {
            throw new BadRequestAlertException("A new statusSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatusSessionDTO result = statusSessionService.save(statusSessionDTO);
        return ResponseEntity.created(new URI("/api/status-sessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /status-sessions : Updates an existing statusSession.
     *
     * @param statusSessionDTO the statusSessionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statusSessionDTO,
     * or with status 400 (Bad Request) if the statusSessionDTO is not valid,
     * or with status 500 (Internal Server Error) if the statusSessionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/status-sessions")
    public ResponseEntity<StatusSessionDTO> updateStatusSession(@RequestBody StatusSessionDTO statusSessionDTO) throws URISyntaxException {
        log.debug("REST request to update StatusSession : {}", statusSessionDTO);
        if (statusSessionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatusSessionDTO result = statusSessionService.save(statusSessionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statusSessionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /status-sessions : get all the statusSessions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of statusSessions in body
     */
    @GetMapping("/status-sessions")
    public ResponseEntity<List<StatusSessionDTO>> getAllStatusSessions(Pageable pageable) {
        log.debug("REST request to get a page of StatusSessions");
        Page<StatusSessionDTO> page = statusSessionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/status-sessions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /status-sessions/:id : get the "id" statusSession.
     *
     * @param id the id of the statusSessionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statusSessionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/status-sessions/{id}")
    public ResponseEntity<StatusSessionDTO> getStatusSession(@PathVariable Long id) {
        log.debug("REST request to get StatusSession : {}", id);
        Optional<StatusSessionDTO> statusSessionDTO = statusSessionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statusSessionDTO);
    }

    /**
     * DELETE  /status-sessions/:id : delete the "id" statusSession.
     *
     * @param id the id of the statusSessionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/status-sessions/{id}")
    public ResponseEntity<Void> deleteStatusSession(@PathVariable Long id) {
        log.debug("REST request to delete StatusSession : {}", id);
        statusSessionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
