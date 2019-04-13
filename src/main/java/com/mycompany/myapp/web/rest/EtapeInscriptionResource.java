package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.EtapeInscriptionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.EtapeInscriptionDTO;
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
 * REST controller for managing EtapeInscription.
 */
@RestController
@RequestMapping("/api")
public class EtapeInscriptionResource {

    private final Logger log = LoggerFactory.getLogger(EtapeInscriptionResource.class);

    private static final String ENTITY_NAME = "etapeInscription";

    private final EtapeInscriptionService etapeInscriptionService;

    public EtapeInscriptionResource(EtapeInscriptionService etapeInscriptionService) {
        this.etapeInscriptionService = etapeInscriptionService;
    }

    /**
     * POST  /etape-inscriptions : Create a new etapeInscription.
     *
     * @param etapeInscriptionDTO the etapeInscriptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new etapeInscriptionDTO, or with status 400 (Bad Request) if the etapeInscription has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/etape-inscriptions")
    public ResponseEntity<EtapeInscriptionDTO> createEtapeInscription(@RequestBody EtapeInscriptionDTO etapeInscriptionDTO) throws URISyntaxException {
        log.debug("REST request to save EtapeInscription : {}", etapeInscriptionDTO);
        if (etapeInscriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new etapeInscription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtapeInscriptionDTO result = etapeInscriptionService.save(etapeInscriptionDTO);
        return ResponseEntity.created(new URI("/api/etape-inscriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /etape-inscriptions : Updates an existing etapeInscription.
     *
     * @param etapeInscriptionDTO the etapeInscriptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated etapeInscriptionDTO,
     * or with status 400 (Bad Request) if the etapeInscriptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the etapeInscriptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/etape-inscriptions")
    public ResponseEntity<EtapeInscriptionDTO> updateEtapeInscription(@RequestBody EtapeInscriptionDTO etapeInscriptionDTO) throws URISyntaxException {
        log.debug("REST request to update EtapeInscription : {}", etapeInscriptionDTO);
        if (etapeInscriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtapeInscriptionDTO result = etapeInscriptionService.save(etapeInscriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, etapeInscriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /etape-inscriptions : get all the etapeInscriptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of etapeInscriptions in body
     */
    @GetMapping("/etape-inscriptions")
    public ResponseEntity<List<EtapeInscriptionDTO>> getAllEtapeInscriptions(Pageable pageable) {
        log.debug("REST request to get a page of EtapeInscriptions");
        Page<EtapeInscriptionDTO> page = etapeInscriptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/etape-inscriptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /etape-inscriptions/:id : get the "id" etapeInscription.
     *
     * @param id the id of the etapeInscriptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the etapeInscriptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/etape-inscriptions/{id}")
    public ResponseEntity<EtapeInscriptionDTO> getEtapeInscription(@PathVariable Long id) {
        log.debug("REST request to get EtapeInscription : {}", id);
        Optional<EtapeInscriptionDTO> etapeInscriptionDTO = etapeInscriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etapeInscriptionDTO);
    }

    /**
     * DELETE  /etape-inscriptions/:id : delete the "id" etapeInscription.
     *
     * @param id the id of the etapeInscriptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/etape-inscriptions/{id}")
    public ResponseEntity<Void> deleteEtapeInscription(@PathVariable Long id) {
        log.debug("REST request to delete EtapeInscription : {}", id);
        etapeInscriptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
