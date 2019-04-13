package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.EtudeUniversitaireService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.EtudeUniversitaireDTO;
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
 * REST controller for managing EtudeUniversitaire.
 */
@RestController
@RequestMapping("/api")
public class EtudeUniversitaireResource {

    private final Logger log = LoggerFactory.getLogger(EtudeUniversitaireResource.class);

    private static final String ENTITY_NAME = "etudeUniversitaire";

    private final EtudeUniversitaireService etudeUniversitaireService;

    public EtudeUniversitaireResource(EtudeUniversitaireService etudeUniversitaireService) {
        this.etudeUniversitaireService = etudeUniversitaireService;
    }

    /**
     * POST  /etude-universitaires : Create a new etudeUniversitaire.
     *
     * @param etudeUniversitaireDTO the etudeUniversitaireDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new etudeUniversitaireDTO, or with status 400 (Bad Request) if the etudeUniversitaire has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/etude-universitaires")
    public ResponseEntity<EtudeUniversitaireDTO> createEtudeUniversitaire(@RequestBody EtudeUniversitaireDTO etudeUniversitaireDTO) throws URISyntaxException {
        log.debug("REST request to save EtudeUniversitaire : {}", etudeUniversitaireDTO);
        if (etudeUniversitaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new etudeUniversitaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtudeUniversitaireDTO result = etudeUniversitaireService.save(etudeUniversitaireDTO);
        return ResponseEntity.created(new URI("/api/etude-universitaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /etude-universitaires : Updates an existing etudeUniversitaire.
     *
     * @param etudeUniversitaireDTO the etudeUniversitaireDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated etudeUniversitaireDTO,
     * or with status 400 (Bad Request) if the etudeUniversitaireDTO is not valid,
     * or with status 500 (Internal Server Error) if the etudeUniversitaireDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/etude-universitaires")
    public ResponseEntity<EtudeUniversitaireDTO> updateEtudeUniversitaire(@RequestBody EtudeUniversitaireDTO etudeUniversitaireDTO) throws URISyntaxException {
        log.debug("REST request to update EtudeUniversitaire : {}", etudeUniversitaireDTO);
        if (etudeUniversitaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtudeUniversitaireDTO result = etudeUniversitaireService.save(etudeUniversitaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, etudeUniversitaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /etude-universitaires : get all the etudeUniversitaires.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of etudeUniversitaires in body
     */
    @GetMapping("/etude-universitaires")
    public ResponseEntity<List<EtudeUniversitaireDTO>> getAllEtudeUniversitaires(Pageable pageable) {
        log.debug("REST request to get a page of EtudeUniversitaires");
        Page<EtudeUniversitaireDTO> page = etudeUniversitaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/etude-universitaires");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /etude-universitaires/:id : get the "id" etudeUniversitaire.
     *
     * @param id the id of the etudeUniversitaireDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the etudeUniversitaireDTO, or with status 404 (Not Found)
     */
    @GetMapping("/etude-universitaires/{id}")
    public ResponseEntity<EtudeUniversitaireDTO> getEtudeUniversitaire(@PathVariable Long id) {
        log.debug("REST request to get EtudeUniversitaire : {}", id);
        Optional<EtudeUniversitaireDTO> etudeUniversitaireDTO = etudeUniversitaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etudeUniversitaireDTO);
    }

    /**
     * DELETE  /etude-universitaires/:id : delete the "id" etudeUniversitaire.
     *
     * @param id the id of the etudeUniversitaireDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/etude-universitaires/{id}")
    public ResponseEntity<Void> deleteEtudeUniversitaire(@PathVariable Long id) {
        log.debug("REST request to delete EtudeUniversitaire : {}", id);
        etudeUniversitaireService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
