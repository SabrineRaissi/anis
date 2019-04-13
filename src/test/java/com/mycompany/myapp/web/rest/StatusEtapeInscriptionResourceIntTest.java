package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.StatusEtapeInscription;
import com.mycompany.myapp.repository.StatusEtapeInscriptionRepository;
import com.mycompany.myapp.service.StatusEtapeInscriptionService;
import com.mycompany.myapp.service.dto.StatusEtapeInscriptionDTO;
import com.mycompany.myapp.service.mapper.StatusEtapeInscriptionMapper;
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
 * Test class for the StatusEtapeInscriptionResource REST controller.
 *
 * @see StatusEtapeInscriptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class StatusEtapeInscriptionResourceIntTest {

    @Autowired
    private StatusEtapeInscriptionRepository statusEtapeInscriptionRepository;

    @Autowired
    private StatusEtapeInscriptionMapper statusEtapeInscriptionMapper;

    @Autowired
    private StatusEtapeInscriptionService statusEtapeInscriptionService;

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

    private MockMvc restStatusEtapeInscriptionMockMvc;

    private StatusEtapeInscription statusEtapeInscription;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatusEtapeInscriptionResource statusEtapeInscriptionResource = new StatusEtapeInscriptionResource(statusEtapeInscriptionService);
        this.restStatusEtapeInscriptionMockMvc = MockMvcBuilders.standaloneSetup(statusEtapeInscriptionResource)
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
    public static StatusEtapeInscription createEntity(EntityManager em) {
        StatusEtapeInscription statusEtapeInscription = new StatusEtapeInscription();
        return statusEtapeInscription;
    }

    @Before
    public void initTest() {
        statusEtapeInscription = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatusEtapeInscription() throws Exception {
        int databaseSizeBeforeCreate = statusEtapeInscriptionRepository.findAll().size();

        // Create the StatusEtapeInscription
        StatusEtapeInscriptionDTO statusEtapeInscriptionDTO = statusEtapeInscriptionMapper.toDto(statusEtapeInscription);
        restStatusEtapeInscriptionMockMvc.perform(post("/api/status-etape-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusEtapeInscriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the StatusEtapeInscription in the database
        List<StatusEtapeInscription> statusEtapeInscriptionList = statusEtapeInscriptionRepository.findAll();
        assertThat(statusEtapeInscriptionList).hasSize(databaseSizeBeforeCreate + 1);
        StatusEtapeInscription testStatusEtapeInscription = statusEtapeInscriptionList.get(statusEtapeInscriptionList.size() - 1);
    }

    @Test
    @Transactional
    public void createStatusEtapeInscriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statusEtapeInscriptionRepository.findAll().size();

        // Create the StatusEtapeInscription with an existing ID
        statusEtapeInscription.setId(1L);
        StatusEtapeInscriptionDTO statusEtapeInscriptionDTO = statusEtapeInscriptionMapper.toDto(statusEtapeInscription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatusEtapeInscriptionMockMvc.perform(post("/api/status-etape-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusEtapeInscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatusEtapeInscription in the database
        List<StatusEtapeInscription> statusEtapeInscriptionList = statusEtapeInscriptionRepository.findAll();
        assertThat(statusEtapeInscriptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStatusEtapeInscriptions() throws Exception {
        // Initialize the database
        statusEtapeInscriptionRepository.saveAndFlush(statusEtapeInscription);

        // Get all the statusEtapeInscriptionList
        restStatusEtapeInscriptionMockMvc.perform(get("/api/status-etape-inscriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statusEtapeInscription.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getStatusEtapeInscription() throws Exception {
        // Initialize the database
        statusEtapeInscriptionRepository.saveAndFlush(statusEtapeInscription);

        // Get the statusEtapeInscription
        restStatusEtapeInscriptionMockMvc.perform(get("/api/status-etape-inscriptions/{id}", statusEtapeInscription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statusEtapeInscription.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingStatusEtapeInscription() throws Exception {
        // Get the statusEtapeInscription
        restStatusEtapeInscriptionMockMvc.perform(get("/api/status-etape-inscriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatusEtapeInscription() throws Exception {
        // Initialize the database
        statusEtapeInscriptionRepository.saveAndFlush(statusEtapeInscription);

        int databaseSizeBeforeUpdate = statusEtapeInscriptionRepository.findAll().size();

        // Update the statusEtapeInscription
        StatusEtapeInscription updatedStatusEtapeInscription = statusEtapeInscriptionRepository.findById(statusEtapeInscription.getId()).get();
        // Disconnect from session so that the updates on updatedStatusEtapeInscription are not directly saved in db
        em.detach(updatedStatusEtapeInscription);
        StatusEtapeInscriptionDTO statusEtapeInscriptionDTO = statusEtapeInscriptionMapper.toDto(updatedStatusEtapeInscription);

        restStatusEtapeInscriptionMockMvc.perform(put("/api/status-etape-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusEtapeInscriptionDTO)))
            .andExpect(status().isOk());

        // Validate the StatusEtapeInscription in the database
        List<StatusEtapeInscription> statusEtapeInscriptionList = statusEtapeInscriptionRepository.findAll();
        assertThat(statusEtapeInscriptionList).hasSize(databaseSizeBeforeUpdate);
        StatusEtapeInscription testStatusEtapeInscription = statusEtapeInscriptionList.get(statusEtapeInscriptionList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingStatusEtapeInscription() throws Exception {
        int databaseSizeBeforeUpdate = statusEtapeInscriptionRepository.findAll().size();

        // Create the StatusEtapeInscription
        StatusEtapeInscriptionDTO statusEtapeInscriptionDTO = statusEtapeInscriptionMapper.toDto(statusEtapeInscription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatusEtapeInscriptionMockMvc.perform(put("/api/status-etape-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusEtapeInscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatusEtapeInscription in the database
        List<StatusEtapeInscription> statusEtapeInscriptionList = statusEtapeInscriptionRepository.findAll();
        assertThat(statusEtapeInscriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatusEtapeInscription() throws Exception {
        // Initialize the database
        statusEtapeInscriptionRepository.saveAndFlush(statusEtapeInscription);

        int databaseSizeBeforeDelete = statusEtapeInscriptionRepository.findAll().size();

        // Delete the statusEtapeInscription
        restStatusEtapeInscriptionMockMvc.perform(delete("/api/status-etape-inscriptions/{id}", statusEtapeInscription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StatusEtapeInscription> statusEtapeInscriptionList = statusEtapeInscriptionRepository.findAll();
        assertThat(statusEtapeInscriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusEtapeInscription.class);
        StatusEtapeInscription statusEtapeInscription1 = new StatusEtapeInscription();
        statusEtapeInscription1.setId(1L);
        StatusEtapeInscription statusEtapeInscription2 = new StatusEtapeInscription();
        statusEtapeInscription2.setId(statusEtapeInscription1.getId());
        assertThat(statusEtapeInscription1).isEqualTo(statusEtapeInscription2);
        statusEtapeInscription2.setId(2L);
        assertThat(statusEtapeInscription1).isNotEqualTo(statusEtapeInscription2);
        statusEtapeInscription1.setId(null);
        assertThat(statusEtapeInscription1).isNotEqualTo(statusEtapeInscription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusEtapeInscriptionDTO.class);
        StatusEtapeInscriptionDTO statusEtapeInscriptionDTO1 = new StatusEtapeInscriptionDTO();
        statusEtapeInscriptionDTO1.setId(1L);
        StatusEtapeInscriptionDTO statusEtapeInscriptionDTO2 = new StatusEtapeInscriptionDTO();
        assertThat(statusEtapeInscriptionDTO1).isNotEqualTo(statusEtapeInscriptionDTO2);
        statusEtapeInscriptionDTO2.setId(statusEtapeInscriptionDTO1.getId());
        assertThat(statusEtapeInscriptionDTO1).isEqualTo(statusEtapeInscriptionDTO2);
        statusEtapeInscriptionDTO2.setId(2L);
        assertThat(statusEtapeInscriptionDTO1).isNotEqualTo(statusEtapeInscriptionDTO2);
        statusEtapeInscriptionDTO1.setId(null);
        assertThat(statusEtapeInscriptionDTO1).isNotEqualTo(statusEtapeInscriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(statusEtapeInscriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(statusEtapeInscriptionMapper.fromId(null)).isNull();
    }
}
