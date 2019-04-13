package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.AppStatusService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.AppStatusDTO;
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
 * REST controller for managing AppStatus.
 */
@RestController
@RequestMapping("/api")
public class AppStatusResource {

    private final Logger log = LoggerFactory.getLogger(AppStatusResource.class);

    private static final String ENTITY_NAME = "appStatus";

    private final AppStatusService appStatusService;

    public AppStatusResource(AppStatusService appStatusService) {
        this.appStatusService = appStatusService;
    }

    /**
     * POST  /app-statuses : Create a new appStatus.
     *
     * @param appStatusDTO the appStatusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appStatusDTO, or with status 400 (Bad Request) if the appStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/app-statuses")
    public ResponseEntity<AppStatusDTO> createAppStatus(@RequestBody AppStatusDTO appStatusDTO) throws URISyntaxException {
        log.debug("REST request to save AppStatus : {}", appStatusDTO);
        if (appStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new appStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppStatusDTO result = appStatusService.save(appStatusDTO);
        return ResponseEntity.created(new URI("/api/app-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /app-statuses : Updates an existing appStatus.
     *
     * @param appStatusDTO the appStatusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appStatusDTO,
     * or with status 400 (Bad Request) if the appStatusDTO is not valid,
     * or with status 500 (Internal Server Error) if the appStatusDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/app-statuses")
    public ResponseEntity<AppStatusDTO> updateAppStatus(@RequestBody AppStatusDTO appStatusDTO) throws URISyntaxException {
        log.debug("REST request to update AppStatus : {}", appStatusDTO);
        if (appStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppStatusDTO result = appStatusService.save(appStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /app-statuses : get all the appStatuses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of appStatuses in body
     */
    @GetMapping("/app-statuses")
    public ResponseEntity<List<AppStatusDTO>> getAllAppStatuses(Pageable pageable) {
        log.debug("REST request to get a page of AppStatuses");
        Page<AppStatusDTO> page = appStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/app-statuses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /app-statuses/:id : get the "id" appStatus.
     *
     * @param id the id of the appStatusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appStatusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/app-statuses/{id}")
    public ResponseEntity<AppStatusDTO> getAppStatus(@PathVariable Long id) {
        log.debug("REST request to get AppStatus : {}", id);
        Optional<AppStatusDTO> appStatusDTO = appStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appStatusDTO);
    }

    /**
     * DELETE  /app-statuses/:id : delete the "id" appStatus.
     *
     * @param id the id of the appStatusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/app-statuses/{id}")
    public ResponseEntity<Void> deleteAppStatus(@PathVariable Long id) {
        log.debug("REST request to delete AppStatus : {}", id);
        appStatusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
