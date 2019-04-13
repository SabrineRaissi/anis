package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.DoctorantService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.DoctorantDTO;
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
 * REST controller for managing Doctorant.
 */
@RestController
@RequestMapping("/api")
public class DoctorantResource {

    private final Logger log = LoggerFactory.getLogger(DoctorantResource.class);

    private static final String ENTITY_NAME = "doctorant";

    private final DoctorantService doctorantService;

    public DoctorantResource(DoctorantService doctorantService) {
        this.doctorantService = doctorantService;
    }

    /**
     * POST  /doctorants : Create a new doctorant.
     *
     * @param doctorantDTO the doctorantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new doctorantDTO, or with status 400 (Bad Request) if the doctorant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/doctorants")
    public ResponseEntity<DoctorantDTO> createDoctorant(@RequestBody DoctorantDTO doctorantDTO) throws URISyntaxException {
        log.debug("REST request to save Doctorant : {}", doctorantDTO);
        if (doctorantDTO.getId() != null) {
            throw new BadRequestAlertException("A new doctorant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoctorantDTO result = doctorantService.save(doctorantDTO);
        return ResponseEntity.created(new URI("/api/doctorants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /doctorants : Updates an existing doctorant.
     *
     * @param doctorantDTO the doctorantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated doctorantDTO,
     * or with status 400 (Bad Request) if the doctorantDTO is not valid,
     * or with status 500 (Internal Server Error) if the doctorantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/doctorants")
    public ResponseEntity<DoctorantDTO> updateDoctorant(@RequestBody DoctorantDTO doctorantDTO) throws URISyntaxException {
        log.debug("REST request to update Doctorant : {}", doctorantDTO);
        if (doctorantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoctorantDTO result = doctorantService.save(doctorantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, doctorantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /doctorants : get all the doctorants.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of doctorants in body
     */
    @GetMapping("/doctorants")
    public ResponseEntity<List<DoctorantDTO>> getAllDoctorants(Pageable pageable) {
        log.debug("REST request to get a page of Doctorants");
        Page<DoctorantDTO> page = doctorantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/doctorants");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /doctorants/:id : get the "id" doctorant.
     *
     * @param id the id of the doctorantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the doctorantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/doctorants/{id}")
    public ResponseEntity<DoctorantDTO> getDoctorant(@PathVariable Long id) {
        log.debug("REST request to get Doctorant : {}", id);
        Optional<DoctorantDTO> doctorantDTO = doctorantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doctorantDTO);
    }

    /**
     * DELETE  /doctorants/:id : delete the "id" doctorant.
     *
     * @param id the id of the doctorantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/doctorants/{id}")
    public ResponseEntity<Void> deleteDoctorant(@PathVariable Long id) {
        log.debug("REST request to delete Doctorant : {}", id);
        doctorantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
