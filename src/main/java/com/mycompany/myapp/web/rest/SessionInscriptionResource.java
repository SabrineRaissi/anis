package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.SessionInscriptionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.SessionInscriptionDTO;
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
 * REST controller for managing SessionInscription.
 */
@RestController
@RequestMapping("/api")
public class SessionInscriptionResource {

    private final Logger log = LoggerFactory.getLogger(SessionInscriptionResource.class);

    private static final String ENTITY_NAME = "sessionInscription";

    private final SessionInscriptionService sessionInscriptionService;

    public SessionInscriptionResource(SessionInscriptionService sessionInscriptionService) {
        this.sessionInscriptionService = sessionInscriptionService;
    }

    /**
     * POST  /session-inscriptions : Create a new sessionInscription.
     *
     * @param sessionInscriptionDTO the sessionInscriptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sessionInscriptionDTO, or with status 400 (Bad Request) if the sessionInscription has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/session-inscriptions")
    public ResponseEntity<SessionInscriptionDTO> createSessionInscription(@RequestBody SessionInscriptionDTO sessionInscriptionDTO) throws URISyntaxException {
        log.debug("REST request to save SessionInscription : {}", sessionInscriptionDTO);
        if (sessionInscriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new sessionInscription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SessionInscriptionDTO result = sessionInscriptionService.save(sessionInscriptionDTO);
        return ResponseEntity.created(new URI("/api/session-inscriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /session-inscriptions : Updates an existing sessionInscription.
     *
     * @param sessionInscriptionDTO the sessionInscriptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sessionInscriptionDTO,
     * or with status 400 (Bad Request) if the sessionInscriptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the sessionInscriptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/session-inscriptions")
    public ResponseEntity<SessionInscriptionDTO> updateSessionInscription(@RequestBody SessionInscriptionDTO sessionInscriptionDTO) throws URISyntaxException {
        log.debug("REST request to update SessionInscription : {}", sessionInscriptionDTO);
        if (sessionInscriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SessionInscriptionDTO result = sessionInscriptionService.save(sessionInscriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sessionInscriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /session-inscriptions : get all the sessionInscriptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sessionInscriptions in body
     */
    @GetMapping("/session-inscriptions")
    public ResponseEntity<List<SessionInscriptionDTO>> getAllSessionInscriptions(Pageable pageable) {
        log.debug("REST request to get a page of SessionInscriptions");
        Page<SessionInscriptionDTO> page = sessionInscriptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/session-inscriptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /session-inscriptions/:id : get the "id" sessionInscription.
     *
     * @param id the id of the sessionInscriptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sessionInscriptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/session-inscriptions/{id}")
    public ResponseEntity<SessionInscriptionDTO> getSessionInscription(@PathVariable Long id) {
        log.debug("REST request to get SessionInscription : {}", id);
        Optional<SessionInscriptionDTO> sessionInscriptionDTO = sessionInscriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sessionInscriptionDTO);
    }

    /**
     * DELETE  /session-inscriptions/:id : delete the "id" sessionInscription.
     *
     * @param id the id of the sessionInscriptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/session-inscriptions/{id}")
    public ResponseEntity<Void> deleteSessionInscription(@PathVariable Long id) {
        log.debug("REST request to delete SessionInscription : {}", id);
        sessionInscriptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
