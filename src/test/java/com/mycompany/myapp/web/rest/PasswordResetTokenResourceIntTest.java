package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.PasswordResetToken;
import com.mycompany.myapp.repository.PasswordResetTokenRepository;
import com.mycompany.myapp.service.PasswordResetTokenService;
import com.mycompany.myapp.service.dto.PasswordResetTokenDTO;
import com.mycompany.myapp.service.mapper.PasswordResetTokenMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PasswordResetTokenResource REST controller.
 *
 * @see PasswordResetTokenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class PasswordResetTokenResourceIntTest {

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EXPIRY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EXPIRY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordResetTokenMapper passwordResetTokenMapper;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPasswordResetTokenMockMvc;

    private PasswordResetToken passwordResetToken;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PasswordResetTokenResource passwordResetTokenResource = new PasswordResetTokenResource(passwordResetTokenService);
        this.restPasswordResetTokenMockMvc = MockMvcBuilders.standaloneSetup(passwordResetTokenResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PasswordResetToken createEntity(EntityManager em) {
        PasswordResetToken passwordResetToken = new PasswordResetToken()
            .token(DEFAULT_TOKEN)
            .expiryDate(DEFAULT_EXPIRY_DATE);
        return passwordResetToken;
    }

    @Before
    public void initTest() {
        passwordResetToken = createEntity(em);
    }

    @Test
    @Transactional
    public void createPasswordResetToken() throws Exception {
        int databaseSizeBeforeCreate = passwordResetTokenRepository.findAll().size();

        // Create the PasswordResetToken
        PasswordResetTokenDTO passwordResetTokenDTO = passwordResetTokenMapper.toDto(passwordResetToken);
        restPasswordResetTokenMockMvc.perform(post("/api/password-reset-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passwordResetTokenDTO)))
            .andExpect(status().isCreated());

        // Validate the PasswordResetToken in the database
        List<PasswordResetToken> passwordResetTokenList = passwordResetTokenRepository.findAll();
        assertThat(passwordResetTokenList).hasSize(databaseSizeBeforeCreate + 1);
        PasswordResetToken testPasswordResetToken = passwordResetTokenList.get(passwordResetTokenList.size() - 1);
        assertThat(testPasswordResetToken.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testPasswordResetToken.getExpiryDate()).isEqualTo(DEFAULT_EXPIRY_DATE);
    }

    @Test
    @Transactional
    public void createPasswordResetTokenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = passwordResetTokenRepository.findAll().size();

        // Create the PasswordResetToken with an existing ID
        passwordResetToken.setId(1L);
        PasswordResetTokenDTO passwordResetTokenDTO = passwordResetTokenMapper.toDto(passwordResetToken);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPasswordResetTokenMockMvc.perform(post("/api/password-reset-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passwordResetTokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PasswordResetToken in the database
        List<PasswordResetToken> passwordResetTokenList = passwordResetTokenRepository.findAll();
        assertThat(passwordResetTokenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPasswordResetTokens() throws Exception {
        // Initialize the database
        passwordResetTokenRepository.saveAndFlush(passwordResetToken);

        // Get all the passwordResetTokenList
        restPasswordResetTokenMockMvc.perform(get("/api/password-reset-tokens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(passwordResetToken.getId().intValue())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.toString())))
            .andExpect(jsonPath("$.[*].expiryDate").value(hasItem(sameInstant(DEFAULT_EXPIRY_DATE))));
    }
    
    @Test
    @Transactional
    public void getPasswordResetToken() throws Exception {
        // Initialize the database
        passwordResetTokenRepository.saveAndFlush(passwordResetToken);

        // Get the passwordResetToken
        restPasswordResetTokenMockMvc.perform(get("/api/password-reset-tokens/{id}", passwordResetToken.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(passwordResetToken.getId().intValue()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.toString()))
            .andExpect(jsonPath("$.expiryDate").value(sameInstant(DEFAULT_EXPIRY_DATE)));
    }

    @Test
    @Transactional
    public void getNonExistingPasswordResetToken() throws Exception {
        // Get the passwordResetToken
        restPasswordResetTokenMockMvc.perform(get("/api/password-reset-tokens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePasswordResetToken() throws Exception {
        // Initialize the database
        passwordResetTokenRepository.saveAndFlush(passwordResetToken);

        int databaseSizeBeforeUpdate = passwordResetTokenRepository.findAll().size();

        // Update the passwordResetToken
        PasswordResetToken updatedPasswordResetToken = passwordResetTokenRepository.findById(passwordResetToken.getId()).get();
        // Disconnect from session so that the updates on updatedPasswordResetToken are not directly saved in db
        em.detach(updatedPasswordResetToken);
        updatedPasswordResetToken
            .token(UPDATED_TOKEN)
            .expiryDate(UPDATED_EXPIRY_DATE);
        PasswordResetTokenDTO passwordResetTokenDTO = passwordResetTokenMapper.toDto(updatedPasswordResetToken);

        restPasswordResetTokenMockMvc.perform(put("/api/password-reset-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passwordResetTokenDTO)))
            .andExpect(status().isOk());

        // Validate the PasswordResetToken in the database
        List<PasswordResetToken> passwordResetTokenList = passwordResetTokenRepository.findAll();
        assertThat(passwordResetTokenList).hasSize(databaseSizeBeforeUpdate);
        PasswordResetToken testPasswordResetToken = passwordResetTokenList.get(passwordResetTokenList.size() - 1);
        assertThat(testPasswordResetToken.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testPasswordResetToken.getExpiryDate()).isEqualTo(UPDATED_EXPIRY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPasswordResetToken() throws Exception {
        int databaseSizeBeforeUpdate = passwordResetTokenRepository.findAll().size();

        // Create the PasswordResetToken
        PasswordResetTokenDTO passwordResetTokenDTO = passwordResetTokenMapper.toDto(passwordResetToken);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPasswordResetTokenMockMvc.perform(put("/api/password-reset-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passwordResetTokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PasswordResetToken in the database
        List<PasswordResetToken> passwordResetTokenList = passwordResetTokenRepository.findAll();
        assertThat(passwordResetTokenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePasswordResetToken() throws Exception {
        // Initialize the database
        passwordResetTokenRepository.saveAndFlush(passwordResetToken);

        int databaseSizeBeforeDelete = passwordResetTokenRepository.findAll().size();

        // Delete the passwordResetToken
        restPasswordResetTokenMockMvc.perform(delete("/api/password-reset-tokens/{id}", passwordResetToken.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PasswordResetToken> passwordResetTokenList = passwordResetTokenRepository.findAll();
        assertThat(passwordResetTokenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PasswordResetToken.class);
        PasswordResetToken passwordResetToken1 = new PasswordResetToken();
        passwordResetToken1.setId(1L);
        PasswordResetToken passwordResetToken2 = new PasswordResetToken();
        passwordResetToken2.setId(passwordResetToken1.getId());
        assertThat(passwordResetToken1).isEqualTo(passwordResetToken2);
        passwordResetToken2.setId(2L);
        assertThat(passwordResetToken1).isNotEqualTo(passwordResetToken2);
        passwordResetToken1.setId(null);
        assertThat(passwordResetToken1).isNotEqualTo(passwordResetToken2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PasswordResetTokenDTO.class);
        PasswordResetTokenDTO passwordResetTokenDTO1 = new PasswordResetTokenDTO();
        passwordResetTokenDTO1.setId(1L);
        PasswordResetTokenDTO passwordResetTokenDTO2 = new PasswordResetTokenDTO();
        assertThat(passwordResetTokenDTO1).isNotEqualTo(passwordResetTokenDTO2);
        passwordResetTokenDTO2.setId(passwordResetTokenDTO1.getId());
        assertThat(passwordResetTokenDTO1).isEqualTo(passwordResetTokenDTO2);
        passwordResetTokenDTO2.setId(2L);
        assertThat(passwordResetTokenDTO1).isNotEqualTo(passwordResetTokenDTO2);
        passwordResetTokenDTO1.setId(null);
        assertThat(passwordResetTokenDTO1).isNotEqualTo(passwordResetTokenDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(passwordResetTokenMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(passwordResetTokenMapper.fromId(null)).isNull();
    }
}
