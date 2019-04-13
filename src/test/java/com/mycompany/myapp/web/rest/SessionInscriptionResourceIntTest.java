package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.SessionInscription;
import com.mycompany.myapp.repository.SessionInscriptionRepository;
import com.mycompany.myapp.service.SessionInscriptionService;
import com.mycompany.myapp.service.dto.SessionInscriptionDTO;
import com.mycompany.myapp.service.mapper.SessionInscriptionMapper;
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

import com.mycompany.myapp.domain.enumeration.Annee;
/**
 * Test class for the SessionInscriptionResource REST controller.
 *
 * @see SessionInscriptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class SessionInscriptionResourceIntTest {

    private static final ZonedDateTime DEFAULT_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Annee DEFAULT_ANNEE = Annee.PREMIERE_ANNEE;
    private static final Annee UPDATED_ANNEE = Annee.DEUXIEME_ANNEE;

    @Autowired
    private SessionInscriptionRepository sessionInscriptionRepository;

    @Autowired
    private SessionInscriptionMapper sessionInscriptionMapper;

    @Autowired
    private SessionInscriptionService sessionInscriptionService;

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

    private MockMvc restSessionInscriptionMockMvc;

    private SessionInscription sessionInscription;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SessionInscriptionResource sessionInscriptionResource = new SessionInscriptionResource(sessionInscriptionService);
        this.restSessionInscriptionMockMvc = MockMvcBuilders.standaloneSetup(sessionInscriptionResource)
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
    public static SessionInscription createEntity(EntityManager em) {
        SessionInscription sessionInscription = new SessionInscription()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .annee(DEFAULT_ANNEE);
        return sessionInscription;
    }

    @Before
    public void initTest() {
        sessionInscription = createEntity(em);
    }

    @Test
    @Transactional
    public void createSessionInscription() throws Exception {
        int databaseSizeBeforeCreate = sessionInscriptionRepository.findAll().size();

        // Create the SessionInscription
        SessionInscriptionDTO sessionInscriptionDTO = sessionInscriptionMapper.toDto(sessionInscription);
        restSessionInscriptionMockMvc.perform(post("/api/session-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sessionInscriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the SessionInscription in the database
        List<SessionInscription> sessionInscriptionList = sessionInscriptionRepository.findAll();
        assertThat(sessionInscriptionList).hasSize(databaseSizeBeforeCreate + 1);
        SessionInscription testSessionInscription = sessionInscriptionList.get(sessionInscriptionList.size() - 1);
        assertThat(testSessionInscription.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSessionInscription.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSessionInscription.getAnnee()).isEqualTo(DEFAULT_ANNEE);
    }

    @Test
    @Transactional
    public void createSessionInscriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sessionInscriptionRepository.findAll().size();

        // Create the SessionInscription with an existing ID
        sessionInscription.setId(1L);
        SessionInscriptionDTO sessionInscriptionDTO = sessionInscriptionMapper.toDto(sessionInscription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSessionInscriptionMockMvc.perform(post("/api/session-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sessionInscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SessionInscription in the database
        List<SessionInscription> sessionInscriptionList = sessionInscriptionRepository.findAll();
        assertThat(sessionInscriptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSessionInscriptions() throws Exception {
        // Initialize the database
        sessionInscriptionRepository.saveAndFlush(sessionInscription);

        // Get all the sessionInscriptionList
        restSessionInscriptionMockMvc.perform(get("/api/session-inscriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sessionInscription.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(sameInstant(DEFAULT_START_DATE))))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(sameInstant(DEFAULT_END_DATE))))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE.toString())));
    }
    
    @Test
    @Transactional
    public void getSessionInscription() throws Exception {
        // Initialize the database
        sessionInscriptionRepository.saveAndFlush(sessionInscription);

        // Get the sessionInscription
        restSessionInscriptionMockMvc.perform(get("/api/session-inscriptions/{id}", sessionInscription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sessionInscription.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(sameInstant(DEFAULT_START_DATE)))
            .andExpect(jsonPath("$.endDate").value(sameInstant(DEFAULT_END_DATE)))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSessionInscription() throws Exception {
        // Get the sessionInscription
        restSessionInscriptionMockMvc.perform(get("/api/session-inscriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSessionInscription() throws Exception {
        // Initialize the database
        sessionInscriptionRepository.saveAndFlush(sessionInscription);

        int databaseSizeBeforeUpdate = sessionInscriptionRepository.findAll().size();

        // Update the sessionInscription
        SessionInscription updatedSessionInscription = sessionInscriptionRepository.findById(sessionInscription.getId()).get();
        // Disconnect from session so that the updates on updatedSessionInscription are not directly saved in db
        em.detach(updatedSessionInscription);
        updatedSessionInscription
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .annee(UPDATED_ANNEE);
        SessionInscriptionDTO sessionInscriptionDTO = sessionInscriptionMapper.toDto(updatedSessionInscription);

        restSessionInscriptionMockMvc.perform(put("/api/session-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sessionInscriptionDTO)))
            .andExpect(status().isOk());

        // Validate the SessionInscription in the database
        List<SessionInscription> sessionInscriptionList = sessionInscriptionRepository.findAll();
        assertThat(sessionInscriptionList).hasSize(databaseSizeBeforeUpdate);
        SessionInscription testSessionInscription = sessionInscriptionList.get(sessionInscriptionList.size() - 1);
        assertThat(testSessionInscription.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSessionInscription.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSessionInscription.getAnnee()).isEqualTo(UPDATED_ANNEE);
    }

    @Test
    @Transactional
    public void updateNonExistingSessionInscription() throws Exception {
        int databaseSizeBeforeUpdate = sessionInscriptionRepository.findAll().size();

        // Create the SessionInscription
        SessionInscriptionDTO sessionInscriptionDTO = sessionInscriptionMapper.toDto(sessionInscription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSessionInscriptionMockMvc.perform(put("/api/session-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sessionInscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SessionInscription in the database
        List<SessionInscription> sessionInscriptionList = sessionInscriptionRepository.findAll();
        assertThat(sessionInscriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSessionInscription() throws Exception {
        // Initialize the database
        sessionInscriptionRepository.saveAndFlush(sessionInscription);

        int databaseSizeBeforeDelete = sessionInscriptionRepository.findAll().size();

        // Delete the sessionInscription
        restSessionInscriptionMockMvc.perform(delete("/api/session-inscriptions/{id}", sessionInscription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SessionInscription> sessionInscriptionList = sessionInscriptionRepository.findAll();
        assertThat(sessionInscriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionInscription.class);
        SessionInscription sessionInscription1 = new SessionInscription();
        sessionInscription1.setId(1L);
        SessionInscription sessionInscription2 = new SessionInscription();
        sessionInscription2.setId(sessionInscription1.getId());
        assertThat(sessionInscription1).isEqualTo(sessionInscription2);
        sessionInscription2.setId(2L);
        assertThat(sessionInscription1).isNotEqualTo(sessionInscription2);
        sessionInscription1.setId(null);
        assertThat(sessionInscription1).isNotEqualTo(sessionInscription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionInscriptionDTO.class);
        SessionInscriptionDTO sessionInscriptionDTO1 = new SessionInscriptionDTO();
        sessionInscriptionDTO1.setId(1L);
        SessionInscriptionDTO sessionInscriptionDTO2 = new SessionInscriptionDTO();
        assertThat(sessionInscriptionDTO1).isNotEqualTo(sessionInscriptionDTO2);
        sessionInscriptionDTO2.setId(sessionInscriptionDTO1.getId());
        assertThat(sessionInscriptionDTO1).isEqualTo(sessionInscriptionDTO2);
        sessionInscriptionDTO2.setId(2L);
        assertThat(sessionInscriptionDTO1).isNotEqualTo(sessionInscriptionDTO2);
        sessionInscriptionDTO1.setId(null);
        assertThat(sessionInscriptionDTO1).isNotEqualTo(sessionInscriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sessionInscriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sessionInscriptionMapper.fromId(null)).isNull();
    }
}
