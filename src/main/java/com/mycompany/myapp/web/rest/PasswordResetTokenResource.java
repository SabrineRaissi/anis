package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.PasswordResetTokenService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.PasswordResetTokenDTO;
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
 * REST controller for managing PasswordResetToken.
 */
@RestController
@RequestMapping("/api")
public class PasswordResetTokenResource {

    private final Logger log = LoggerFactory.getLogger(PasswordResetTokenResource.class);

    private static final String ENTITY_NAME = "passwordResetToken";

    private final PasswordResetTokenService passwordResetTokenService;

    public PasswordResetTokenResource(PasswordResetTokenService passwordResetTokenService) {
        this.passwordResetTokenService = passwordResetTokenService;
    }

    /**
     * POST  /password-reset-tokens : Create a new passwordResetToken.
     *
     * @param passwordResetTokenDTO the passwordResetTokenDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new passwordResetTokenDTO, or with status 400 (Bad Request) if the passwordResetToken has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/password-reset-tokens")
    public ResponseEntity<PasswordResetTokenDTO> createPasswordResetToken(@RequestBody PasswordResetTokenDTO passwordResetTokenDTO) throws URISyntaxException {
        log.debug("REST request to save PasswordResetToken : {}", passwordResetTokenDTO);
        if (passwordResetTokenDTO.getId() != null) {
            throw new BadRequestAlertException("A new passwordResetToken cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PasswordResetTokenDTO result = passwordResetTokenService.save(passwordResetTokenDTO);
        return ResponseEntity.created(new URI("/api/password-reset-tokens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /password-reset-tokens : Updates an existing passwordResetToken.
     *
     * @param passwordResetTokenDTO the passwordResetTokenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated passwordResetTokenDTO,
     * or with status 400 (Bad Request) if the passwordResetTokenDTO is not valid,
     * or with status 500 (Internal Server Error) if the passwordResetTokenDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/password-reset-tokens")
    public ResponseEntity<PasswordResetTokenDTO> updatePasswordResetToken(@RequestBody PasswordResetTokenDTO passwordResetTokenDTO) throws URISyntaxException {
        log.debug("REST request to update PasswordResetToken : {}", passwordResetTokenDTO);
        if (passwordResetTokenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PasswordResetTokenDTO result = passwordResetTokenService.save(passwordResetTokenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, passwordResetTokenDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /password-reset-tokens : get all the passwordResetTokens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of passwordResetTokens in body
     */
    @GetMapping("/password-reset-tokens")
    public ResponseEntity<List<PasswordResetTokenDTO>> getAllPasswordResetTokens(Pageable pageable) {
        log.debug("REST request to get a page of PasswordResetTokens");
        Page<PasswordResetTokenDTO> page = passwordResetTokenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/password-reset-tokens");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /password-reset-tokens/:id : get the "id" passwordResetToken.
     *
     * @param id the id of the passwordResetTokenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the passwordResetTokenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/password-reset-tokens/{id}")
    public ResponseEntity<PasswordResetTokenDTO> getPasswordResetToken(@PathVariable Long id) {
        log.debug("REST request to get PasswordResetToken : {}", id);
        Optional<PasswordResetTokenDTO> passwordResetTokenDTO = passwordResetTokenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(passwordResetTokenDTO);
    }

    /**
     * DELETE  /password-reset-tokens/:id : delete the "id" passwordResetToken.
     *
     * @param id the id of the passwordResetTokenDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/password-reset-tokens/{id}")
    public ResponseEntity<Void> deletePasswordResetToken(@PathVariable Long id) {
        log.debug("REST request to delete PasswordResetToken : {}", id);
        passwordResetTokenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
