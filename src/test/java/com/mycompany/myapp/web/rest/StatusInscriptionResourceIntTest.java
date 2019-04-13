package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.StatusInscription;
import com.mycompany.myapp.repository.StatusInscriptionRepository;
import com.mycompany.myapp.service.StatusInscriptionService;
import com.mycompany.myapp.service.dto.StatusInscriptionDTO;
import com.mycompany.myapp.service.mapper.StatusInscriptionMapper;
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
 * Test class for the StatusInscriptionResource REST controller.
 *
 * @see StatusInscriptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class StatusInscriptionResourceIntTest {

    @Autowired
    private StatusInscriptionRepository statusInscriptionRepository;

    @Autowired
    private StatusInscriptionMapper statusInscriptionMapper;

    @Autowired
    private StatusInscriptionService statusInscriptionService;

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

    private MockMvc restStatusInscriptionMockMvc;

    private StatusInscription statusInscription;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatusInscriptionResource statusInscriptionResource = new StatusInscriptionResource(statusInscriptionService);
        this.restStatusInscriptionMockMvc = MockMvcBuilders.standaloneSetup(statusInscriptionResource)
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
    public static StatusInscription createEntity(EntityManager em) {
        StatusInscription statusInscription = new StatusInscription();
        return statusInscription;
    }

    @Before
    public void initTest() {
        statusInscription = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatusInscription() throws Exception {
        int databaseSizeBeforeCreate = statusInscriptionRepository.findAll().size();

        // Create the StatusInscription
        StatusInscriptionDTO statusInscriptionDTO = statusInscriptionMapper.toDto(statusInscription);
        restStatusInscriptionMockMvc.perform(post("/api/status-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusInscriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the StatusInscription in the database
        List<StatusInscription> statusInscriptionList = statusInscriptionRepository.findAll();
        assertThat(statusInscriptionList).hasSize(databaseSizeBeforeCreate + 1);
        StatusInscription testStatusInscription = statusInscriptionList.get(statusInscriptionList.size() - 1);
    }

    @Test
    @Transactional
    public void createStatusInscriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statusInscriptionRepository.findAll().size();

        // Create the StatusInscription with an existing ID
        statusInscription.setId(1L);
        StatusInscriptionDTO statusInscriptionDTO = statusInscriptionMapper.toDto(statusInscription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatusInscriptionMockMvc.perform(post("/api/status-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusInscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatusInscription in the database
        List<StatusInscription> statusInscriptionList = statusInscriptionRepository.findAll();
        assertThat(statusInscriptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStatusInscriptions() throws Exception {
        // Initialize the database
        statusInscriptionRepository.saveAndFlush(statusInscription);

        // Get all the statusInscriptionList
        restStatusInscriptionMockMvc.perform(get("/api/status-inscriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statusInscription.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getStatusInscription() throws Exception {
        // Initialize the database
        statusInscriptionRepository.saveAndFlush(statusInscription);

        // Get the statusInscription
        restStatusInscriptionMockMvc.perform(get("/api/status-inscriptions/{id}", statusInscription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statusInscription.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingStatusInscription() throws Exception {
        // Get the statusInscription
        restStatusInscriptionMockMvc.perform(get("/api/status-inscriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatusInscription() throws Exception {
        // Initialize the database
        statusInscriptionRepository.saveAndFlush(statusInscription);

        int databaseSizeBeforeUpdate = statusInscriptionRepository.findAll().size();

        // Update the statusInscription
        StatusInscription updatedStatusInscription = statusInscriptionRepository.findById(statusInscription.getId()).get();
        // Disconnect from session so that the updates on updatedStatusInscription are not directly saved in db
        em.detach(updatedStatusInscription);
        StatusInscriptionDTO statusInscriptionDTO = statusInscriptionMapper.toDto(updatedStatusInscription);

        restStatusInscriptionMockMvc.perform(put("/api/status-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusInscriptionDTO)))
            .andExpect(status().isOk());

        // Validate the StatusInscription in the database
        List<StatusInscription> statusInscriptionList = statusInscriptionRepository.findAll();
        assertThat(statusInscriptionList).hasSize(databaseSizeBeforeUpdate);
        StatusInscription testStatusInscription = statusInscriptionList.get(statusInscriptionList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingStatusInscription() throws Exception {
        int databaseSizeBeforeUpdate = statusInscriptionRepository.findAll().size();

        // Create the StatusInscription
        StatusInscriptionDTO statusInscriptionDTO = statusInscriptionMapper.toDto(statusInscription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatusInscriptionMockMvc.perform(put("/api/status-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusInscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatusInscription in the database
        List<StatusInscription> statusInscriptionList = statusInscriptionRepository.findAll();
        assertThat(statusInscriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatusInscription() throws Exception {
        // Initialize the database
        statusInscriptionRepository.saveAndFlush(statusInscription);

        int databaseSizeBeforeDelete = statusInscriptionRepository.findAll().size();

        // Delete the statusInscription
        restStatusInscriptionMockMvc.perform(delete("/api/status-inscriptions/{id}", statusInscription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StatusInscription> statusInscriptionList = statusInscriptionRepository.findAll();
        assertThat(statusInscriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusInscription.class);
        StatusInscription statusInscription1 = new StatusInscription();
        statusInscription1.setId(1L);
        StatusInscription statusInscription2 = new StatusInscription();
        statusInscription2.setId(statusInscription1.getId());
        assertThat(statusInscription1).isEqualTo(statusInscription2);
        statusInscription2.setId(2L);
        assertThat(statusInscription1).isNotEqualTo(statusInscription2);
        statusInscription1.setId(null);
        assertThat(statusInscription1).isNotEqualTo(statusInscription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusInscriptionDTO.class);
        StatusInscriptionDTO statusInscriptionDTO1 = new StatusInscriptionDTO();
        statusInscriptionDTO1.setId(1L);
        StatusInscriptionDTO statusInscriptionDTO2 = new StatusInscriptionDTO();
        assertThat(statusInscriptionDTO1).isNotEqualTo(statusInscriptionDTO2);
        statusInscriptionDTO2.setId(statusInscriptionDTO1.getId());
        assertThat(statusInscriptionDTO1).isEqualTo(statusInscriptionDTO2);
        statusInscriptionDTO2.setId(2L);
        assertThat(statusInscriptionDTO1).isNotEqualTo(statusInscriptionDTO2);
        statusInscriptionDTO1.setId(null);
        assertThat(statusInscriptionDTO1).isNotEqualTo(statusInscriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(statusInscriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(statusInscriptionMapper.fromId(null)).isNull();
    }
}
