package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.LastThesesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.LastThesesDTO;
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
 * REST controller for managing LastTheses.
 */
@RestController
@RequestMapping("/api")
public class LastThesesResource {

    private final Logger log = LoggerFactory.getLogger(LastThesesResource.class);

    private static final String ENTITY_NAME = "lastTheses";

    private final LastThesesService lastThesesService;

    public LastThesesResource(LastThesesService lastThesesService) {
        this.lastThesesService = lastThesesService;
    }

    /**
     * POST  /last-theses : Create a new lastTheses.
     *
     * @param lastThesesDTO the lastThesesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lastThesesDTO, or with status 400 (Bad Request) if the lastTheses has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/last-theses")
    public ResponseEntity<LastThesesDTO> createLastTheses(@RequestBody LastThesesDTO lastThesesDTO) throws URISyntaxException {
        log.debug("REST request to save LastTheses : {}", lastThesesDTO);
        if (lastThesesDTO.getId() != null) {
            throw new BadRequestAlertException("A new lastTheses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LastThesesDTO result = lastThesesService.save(lastThesesDTO);
        return ResponseEntity.created(new URI("/api/last-theses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /last-theses : Updates an existing lastTheses.
     *
     * @param lastThesesDTO the lastThesesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated lastThesesDTO,
     * or with status 400 (Bad Request) if the lastThesesDTO is not valid,
     * or with status 500 (Internal Server Error) if the lastThesesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/last-theses")
    public ResponseEntity<LastThesesDTO> updateLastTheses(@RequestBody LastThesesDTO lastThesesDTO) throws URISyntaxException {
        log.debug("REST request to update LastTheses : {}", lastThesesDTO);
        if (lastThesesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LastThesesDTO result = lastThesesService.save(lastThesesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, lastThesesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /last-theses : get all the lastTheses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of lastTheses in body
     */
    @GetMapping("/last-theses")
    public ResponseEntity<List<LastThesesDTO>> getAllLastTheses(Pageable pageable) {
        log.debug("REST request to get a page of LastTheses");
        Page<LastThesesDTO> page = lastThesesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/last-theses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /last-theses/:id : get the "id" lastTheses.
     *
     * @param id the id of the lastThesesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lastThesesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/last-theses/{id}")
    public ResponseEntity<LastThesesDTO> getLastTheses(@PathVariable Long id) {
        log.debug("REST request to get LastTheses : {}", id);
        Optional<LastThesesDTO> lastThesesDTO = lastThesesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lastThesesDTO);
    }

    /**
     * DELETE  /last-theses/:id : delete the "id" lastTheses.
     *
     * @param id the id of the lastThesesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/last-theses/{id}")
    public ResponseEntity<Void> deleteLastTheses(@PathVariable Long id) {
        log.debug("REST request to delete LastTheses : {}", id);
        lastThesesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
