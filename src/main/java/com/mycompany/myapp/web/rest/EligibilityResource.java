package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.EligibilityService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.EligibilityDTO;
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
 * REST controller for managing Eligibility.
 */
@RestController
@RequestMapping("/api")
public class EligibilityResource {

    private final Logger log = LoggerFactory.getLogger(EligibilityResource.class);

    private static final String ENTITY_NAME = "eligibility";

    private final EligibilityService eligibilityService;

    public EligibilityResource(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    /**
     * POST  /eligibilities : Create a new eligibility.
     *
     * @param eligibilityDTO the eligibilityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eligibilityDTO, or with status 400 (Bad Request) if the eligibility has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/eligibilities")
    public ResponseEntity<EligibilityDTO> createEligibility(@RequestBody EligibilityDTO eligibilityDTO) throws URISyntaxException {
        log.debug("REST request to save Eligibility : {}", eligibilityDTO);
        if (eligibilityDTO.getId() != null) {
            throw new BadRequestAlertException("A new eligibility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EligibilityDTO result = eligibilityService.save(eligibilityDTO);
        return ResponseEntity.created(new URI("/api/eligibilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /eligibilities : Updates an existing eligibility.
     *
     * @param eligibilityDTO the eligibilityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eligibilityDTO,
     * or with status 400 (Bad Request) if the eligibilityDTO is not valid,
     * or with status 500 (Internal Server Error) if the eligibilityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/eligibilities")
    public ResponseEntity<EligibilityDTO> updateEligibility(@RequestBody EligibilityDTO eligibilityDTO) throws URISyntaxException {
        log.debug("REST request to update Eligibility : {}", eligibilityDTO);
        if (eligibilityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EligibilityDTO result = eligibilityService.save(eligibilityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eligibilityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /eligibilities : get all the eligibilities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eligibilities in body
     */
    @GetMapping("/eligibilities")
    public ResponseEntity<List<EligibilityDTO>> getAllEligibilities(Pageable pageable) {
        log.debug("REST request to get a page of Eligibilities");
        Page<EligibilityDTO> page = eligibilityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/eligibilities");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /eligibilities/:id : get the "id" eligibility.
     *
     * @param id the id of the eligibilityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eligibilityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/eligibilities/{id}")
    public ResponseEntity<EligibilityDTO> getEligibility(@PathVariable Long id) {
        log.debug("REST request to get Eligibility : {}", id);
        Optional<EligibilityDTO> eligibilityDTO = eligibilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eligibilityDTO);
    }

    /**
     * DELETE  /eligibilities/:id : delete the "id" eligibility.
     *
     * @param id the id of the eligibilityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/eligibilities/{id}")
    public ResponseEntity<Void> deleteEligibility(@PathVariable Long id) {
        log.debug("REST request to delete Eligibility : {}", id);
        eligibilityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
