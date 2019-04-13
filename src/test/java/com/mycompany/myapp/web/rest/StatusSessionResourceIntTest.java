package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.StatusSession;
import com.mycompany.myapp.repository.StatusSessionRepository;
import com.mycompany.myapp.service.StatusSessionService;
import com.mycompany.myapp.service.dto.StatusSessionDTO;
import com.mycompany.myapp.service.mapper.StatusSessionMapper;
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
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StatusSessionResource REST controller.
 *
 * @see StatusSessionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class StatusSessionResourceIntTest {

    @Autowired
    private StatusSessionRepository statusSessionRepository;

    @Autowired
    private StatusSessionMapper statusSessionMapper;

    @Autowired
    private StatusSessionService statusSessionService;

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

    private MockMvc restStatusSessionMockMvc;

    private StatusSession statusSession;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatusSessionResource statusSessionResource = new StatusSessionResource(statusSessionService);
        this.restStatusSessionMockMvc = MockMvcBuilders.standaloneSetup(statusSessionResource)
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
    public static StatusSession createEntity(EntityManager em) {
        StatusSession statusSession = new StatusSession();
        return statusSession;
    }

    @Before
    public void initTest() {
        statusSession = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatusSession() throws Exception {
        int databaseSizeBeforeCreate = statusSessionRepository.findAll().size();

        // Create the StatusSession
        StatusSessionDTO statusSessionDTO = statusSessionMapper.toDto(statusSession);
        restStatusSessionMockMvc.perform(post("/api/status-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusSessionDTO)))
            .andExpect(status().isCreated());

        // Validate the StatusSession in the database
        List<StatusSession> statusSessionList = statusSessionRepository.findAll();
        assertThat(statusSessionList).hasSize(databaseSizeBeforeCreate + 1);
        StatusSession testStatusSession = statusSessionList.get(statusSessionList.size() - 1);
    }

    @Test
    @Transactional
    public void createStatusSessionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statusSessionRepository.findAll().size();

        // Create the StatusSession with an existing ID
        statusSession.setId(1L);
        StatusSessionDTO statusSessionDTO = statusSessionMapper.toDto(statusSession);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatusSessionMockMvc.perform(post("/api/status-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatusSession in the database
        List<StatusSession> statusSessionList = statusSessionRepository.findAll();
        assertThat(statusSessionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStatusSessions() throws Exception {
        // Initialize the database
        statusSessionRepository.saveAndFlush(statusSession);

        // Get all the statusSessionList
        restStatusSessionMockMvc.perform(get("/api/status-sessions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statusSession.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getStatusSession() throws Exception {
        // Initialize the database
        statusSessionRepository.saveAndFlush(statusSession);

        // Get the statusSession
        restStatusSessionMockMvc.perform(get("/api/status-sessions/{id}", statusSession.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statusSession.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingStatusSession() throws Exception {
        // Get the statusSession
        restStatusSessionMockMvc.perform(get("/api/status-sessions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatusSession() throws Exception {
        // Initialize the database
        statusSessionRepository.saveAndFlush(statusSession);

        int databaseSizeBeforeUpdate = statusSessionRepository.findAll().size();

        // Update the statusSession
        StatusSession updatedStatusSession = statusSessionRepository.findById(statusSession.getId()).get();
        // Disconnect from session so that the updates on updatedStatusSession are not directly saved in db
        em.detach(updatedStatusSession);
        StatusSessionDTO statusSessionDTO = statusSessionMapper.toDto(updatedStatusSession);

        restStatusSessionMockMvc.perform(put("/api/status-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusSessionDTO)))
            .andExpect(status().isOk());

        // Validate the StatusSession in the database
        List<StatusSession> statusSessionList = statusSessionRepository.findAll();
        assertThat(statusSessionList).hasSize(databaseSizeBeforeUpdate);
        StatusSession testStatusSession = statusSessionList.get(statusSessionList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingStatusSession() throws Exception {
        int databaseSizeBeforeUpdate = statusSessionRepository.findAll().size();

        // Create the StatusSession
        StatusSessionDTO statusSessionDTO = statusSessionMapper.toDto(statusSession);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatusSessionMockMvc.perform(put("/api/status-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatusSession in the database
        List<StatusSession> statusSessionList = statusSessionRepository.findAll();
        assertThat(statusSessionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatusSession() throws Exception {
        // Initialize the database
        statusSessionRepository.saveAndFlush(statusSession);

        int databaseSizeBeforeDelete = statusSessionRepository.findAll().size();

        // Delete the statusSession
        restStatusSessionMockMvc.perform(delete("/api/status-sessions/{id}", statusSession.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StatusSession> statusSessionList = statusSessionRepository.findAll();
        assertThat(statusSessionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusSession.class);
        StatusSession statusSession1 = new StatusSession();
        statusSession1.setId(1L);
        StatusSession statusSession2 = new StatusSession();
        statusSession2.setId(statusSession1.getId());
        assertThat(statusSession1).isEqualTo(statusSession2);
        statusSession2.setId(2L);
        assertThat(statusSession1).isNotEqualTo(statusSession2);
        statusSession1.setId(null);
        assertThat(statusSession1).isNotEqualTo(statusSession2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusSessionDTO.class);
        StatusSessionDTO statusSessionDTO1 = new StatusSessionDTO();
        statusSessionDTO1.setId(1L);
        StatusSessionDTO statusSessionDTO2 = new StatusSessionDTO();
        assertThat(statusSessionDTO1).isNotEqualTo(statusSessionDTO2);
        statusSessionDTO2.setId(statusSessionDTO1.getId());
        assertThat(statusSessionDTO1).isEqualTo(statusSessionDTO2);
        statusSessionDTO2.setId(2L);
        assertThat(statusSessionDTO1).isNotEqualTo(statusSessionDTO2);
        statusSessionDTO1.setId(null);
        assertThat(statusSessionDTO1).isNotEqualTo(statusSessionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(statusSessionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(statusSessionMapper.fromId(null)).isNull();
    }
}
