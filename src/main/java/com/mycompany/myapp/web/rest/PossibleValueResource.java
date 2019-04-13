package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.PossibleValueService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.PossibleValueDTO;
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
 * REST controller for managing PossibleValue.
 */
@RestController
@RequestMapping("/api")
public class PossibleValueResource {

    private final Logger log = LoggerFactory.getLogger(PossibleValueResource.class);

    private static final String ENTITY_NAME = "possibleValue";

    private final PossibleValueService possibleValueService;

    public PossibleValueResource(PossibleValueService possibleValueService) {
        this.possibleValueService = possibleValueService;
    }

    /**
     * POST  /possible-values : Create a new possibleValue.
     *
     * @param possibleValueDTO the possibleValueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new possibleValueDTO, or with status 400 (Bad Request) if the possibleValue has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/possible-values")
    public ResponseEntity<PossibleValueDTO> createPossibleValue(@RequestBody PossibleValueDTO possibleValueDTO) throws URISyntaxException {
        log.debug("REST request to save PossibleValue : {}", possibleValueDTO);
        if (possibleValueDTO.getId() != null) {
            throw new BadRequestAlertException("A new possibleValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PossibleValueDTO result = possibleValueService.save(possibleValueDTO);
        return ResponseEntity.created(new URI("/api/possible-values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /possible-values : Updates an existing possibleValue.
     *
     * @param possibleValueDTO the possibleValueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated possibleValueDTO,
     * or with status 400 (Bad Request) if the possibleValueDTO is not valid,
     * or with status 500 (Internal Server Error) if the possibleValueDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/possible-values")
    public ResponseEntity<PossibleValueDTO> updatePossibleValue(@RequestBody PossibleValueDTO possibleValueDTO) throws URISyntaxException {
        log.debug("REST request to update PossibleValue : {}", possibleValueDTO);
        if (possibleValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PossibleValueDTO result = possibleValueService.save(possibleValueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, possibleValueDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /possible-values : get all the possibleValues.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of possibleValues in body
     */
    @GetMapping("/possible-values")
    public ResponseEntity<List<PossibleValueDTO>> getAllPossibleValues(Pageable pageable) {
        log.debug("REST request to get a page of PossibleValues");
        Page<PossibleValueDTO> page = possibleValueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/possible-values");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /possible-values/:id : get the "id" possibleValue.
     *
     * @param id the id of the possibleValueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the possibleValueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/possible-values/{id}")
    public ResponseEntity<PossibleValueDTO> getPossibleValue(@PathVariable Long id) {
        log.debug("REST request to get PossibleValue : {}", id);
        Optional<PossibleValueDTO> possibleValueDTO = possibleValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(possibleValueDTO);
    }

    /**
     * DELETE  /possible-values/:id : delete the "id" possibleValue.
     *
     * @param id the id of the possibleValueDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/possible-values/{id}")
    public ResponseEntity<Void> deletePossibleValue(@PathVariable Long id) {
        log.debug("REST request to delete PossibleValue : {}", id);
        possibleValueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
