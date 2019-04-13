package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.DeviceMetadataService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.DeviceMetadataDTO;
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
 * REST controller for managing DeviceMetadata.
 */
@RestController
@RequestMapping("/api")
public class DeviceMetadataResource {

    private final Logger log = LoggerFactory.getLogger(DeviceMetadataResource.class);

    private static final String ENTITY_NAME = "deviceMetadata";

    private final DeviceMetadataService deviceMetadataService;

    public DeviceMetadataResource(DeviceMetadataService deviceMetadataService) {
        this.deviceMetadataService = deviceMetadataService;
    }

    /**
     * POST  /device-metadata : Create a new deviceMetadata.
     *
     * @param deviceMetadataDTO the deviceMetadataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deviceMetadataDTO, or with status 400 (Bad Request) if the deviceMetadata has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/device-metadata")
    public ResponseEntity<DeviceMetadataDTO> createDeviceMetadata(@RequestBody DeviceMetadataDTO deviceMetadataDTO) throws URISyntaxException {
        log.debug("REST request to save DeviceMetadata : {}", deviceMetadataDTO);
        if (deviceMetadataDTO.getId() != null) {
            throw new BadRequestAlertException("A new deviceMetadata cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeviceMetadataDTO result = deviceMetadataService.save(deviceMetadataDTO);
        return ResponseEntity.created(new URI("/api/device-metadata/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /device-metadata : Updates an existing deviceMetadata.
     *
     * @param deviceMetadataDTO the deviceMetadataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deviceMetadataDTO,
     * or with status 400 (Bad Request) if the deviceMetadataDTO is not valid,
     * or with status 500 (Internal Server Error) if the deviceMetadataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/device-metadata")
    public ResponseEntity<DeviceMetadataDTO> updateDeviceMetadata(@RequestBody DeviceMetadataDTO deviceMetadataDTO) throws URISyntaxException {
        log.debug("REST request to update DeviceMetadata : {}", deviceMetadataDTO);
        if (deviceMetadataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeviceMetadataDTO result = deviceMetadataService.save(deviceMetadataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deviceMetadataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /device-metadata : get all the deviceMetadata.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deviceMetadata in body
     */
    @GetMapping("/device-metadata")
    public ResponseEntity<List<DeviceMetadataDTO>> getAllDeviceMetadata(Pageable pageable) {
        log.debug("REST request to get a page of DeviceMetadata");
        Page<DeviceMetadataDTO> page = deviceMetadataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/device-metadata");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /device-metadata/:id : get the "id" deviceMetadata.
     *
     * @param id the id of the deviceMetadataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deviceMetadataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/device-metadata/{id}")
    public ResponseEntity<DeviceMetadataDTO> getDeviceMetadata(@PathVariable Long id) {
        log.debug("REST request to get DeviceMetadata : {}", id);
        Optional<DeviceMetadataDTO> deviceMetadataDTO = deviceMetadataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deviceMetadataDTO);
    }

    /**
     * DELETE  /device-metadata/:id : delete the "id" deviceMetadata.
     *
     * @param id the id of the deviceMetadataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/device-metadata/{id}")
    public ResponseEntity<Void> deleteDeviceMetadata(@PathVariable Long id) {
        log.debug("REST request to delete DeviceMetadata : {}", id);
        deviceMetadataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
