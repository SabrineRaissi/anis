package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.TheseService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.TheseDTO;
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
 * REST controller for managing These.
 */
@RestController
@RequestMapping("/api")
public class TheseResource {

    private final Logger log = LoggerFactory.getLogger(TheseResource.class);

    private static final String ENTITY_NAME = "these";

    private final TheseService theseService;

    public TheseResource(TheseService theseService) {
        this.theseService = theseService;
    }

    /**
     * POST  /these : Create a new these.
     *
     * @param theseDTO the theseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new theseDTO, or with status 400 (Bad Request) if the these has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/these")
    public ResponseEntity<TheseDTO> createThese(@RequestBody TheseDTO theseDTO) throws URISyntaxException {
        log.debug("REST request to save These : {}", theseDTO);
        if (theseDTO.getId() != null) {
            throw new BadRequestAlertException("A new these cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TheseDTO result = theseService.save(theseDTO);
        return ResponseEntity.created(new URI("/api/these/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /these : Updates an existing these.
     *
     * @param theseDTO the theseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated theseDTO,
     * or with status 400 (Bad Request) if the theseDTO is not valid,
     * or with status 500 (Internal Server Error) if the theseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/these")
    public ResponseEntity<TheseDTO> updateThese(@RequestBody TheseDTO theseDTO) throws URISyntaxException {
        log.debug("REST request to update These : {}", theseDTO);
        if (theseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TheseDTO result = theseService.save(theseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, theseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /these : get all the these.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of these in body
     */
    @GetMapping("/these")
    public ResponseEntity<List<TheseDTO>> getAllThese(Pageable pageable) {
        log.debug("REST request to get a page of These");
        Page<TheseDTO> page = theseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/these");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /these/:id : get the "id" these.
     *
     * @param id the id of the theseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the theseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/these/{id}")
    public ResponseEntity<TheseDTO> getThese(@PathVariable Long id) {
        log.debug("REST request to get These : {}", id);
        Optional<TheseDTO> theseDTO = theseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(theseDTO);
    }

    /**
     * DELETE  /these/:id : delete the "id" these.
     *
     * @param id the id of the theseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/these/{id}")
    public ResponseEntity<Void> deleteThese(@PathVariable Long id) {
        log.debug("REST request to delete These : {}", id);
        theseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
