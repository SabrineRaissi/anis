package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.LastTheses;
import com.mycompany.myapp.repository.LastThesesRepository;
import com.mycompany.myapp.service.LastThesesService;
import com.mycompany.myapp.service.dto.LastThesesDTO;
import com.mycompany.myapp.service.mapper.LastThesesMapper;
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
 * Test class for the LastThesesResource REST controller.
 *
 * @see LastThesesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class LastThesesResourceIntTest {

    private static final String DEFAULT_ANNEE_UNIVERSITAIRE = "AAAAAAAAAA";
    private static final String UPDATED_ANNEE_UNIVERSITAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_ETABLISSEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ETABLISSEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_SUJET_THESE = "AAAAAAAAAA";
    private static final String UPDATED_SUJET_THESE = "BBBBBBBBBB";

    @Autowired
    private LastThesesRepository lastThesesRepository;

    @Autowired
    private LastThesesMapper lastThesesMapper;

    @Autowired
    private LastThesesService lastThesesService;

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

    private MockMvc restLastThesesMockMvc;

    private LastTheses lastTheses;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LastThesesResource lastThesesResource = new LastThesesResource(lastThesesService);
        this.restLastThesesMockMvc = MockMvcBuilders.standaloneSetup(lastThesesResource)
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
    public static LastTheses createEntity(EntityManager em) {
        LastTheses lastTheses = new LastTheses()
            .anneeUniversitaire(DEFAULT_ANNEE_UNIVERSITAIRE)
            .etablissement(DEFAULT_ETABLISSEMENT)
            .sujetThese(DEFAULT_SUJET_THESE);
        return lastTheses;
    }

    @Before
    public void initTest() {
        lastTheses = createEntity(em);
    }

    @Test
    @Transactional
    public void createLastTheses() throws Exception {
        int databaseSizeBeforeCreate = lastThesesRepository.findAll().size();

        // Create the LastTheses
        LastThesesDTO lastThesesDTO = lastThesesMapper.toDto(lastTheses);
        restLastThesesMockMvc.perform(post("/api/last-theses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lastThesesDTO)))
            .andExpect(status().isCreated());

        // Validate the LastTheses in the database
        List<LastTheses> lastThesesList = lastThesesRepository.findAll();
        assertThat(lastThesesList).hasSize(databaseSizeBeforeCreate + 1);
        LastTheses testLastTheses = lastThesesList.get(lastThesesList.size() - 1);
        assertThat(testLastTheses.getAnneeUniversitaire()).isEqualTo(DEFAULT_ANNEE_UNIVERSITAIRE);
        assertThat(testLastTheses.getEtablissement()).isEqualTo(DEFAULT_ETABLISSEMENT);
        assertThat(testLastTheses.getSujetThese()).isEqualTo(DEFAULT_SUJET_THESE);
    }

    @Test
    @Transactional
    public void createLastThesesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lastThesesRepository.findAll().size();

        // Create the LastTheses with an existing ID
        lastTheses.setId(1L);
        LastThesesDTO lastThesesDTO = lastThesesMapper.toDto(lastTheses);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLastThesesMockMvc.perform(post("/api/last-theses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lastThesesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LastTheses in the database
        List<LastTheses> lastThesesList = lastThesesRepository.findAll();
        assertThat(lastThesesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLastTheses() throws Exception {
        // Initialize the database
        lastThesesRepository.saveAndFlush(lastTheses);

        // Get all the lastThesesList
        restLastThesesMockMvc.perform(get("/api/last-theses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lastTheses.getId().intValue())))
            .andExpect(jsonPath("$.[*].anneeUniversitaire").value(hasItem(DEFAULT_ANNEE_UNIVERSITAIRE.toString())))
            .andExpect(jsonPath("$.[*].etablissement").value(hasItem(DEFAULT_ETABLISSEMENT.toString())))
            .andExpect(jsonPath("$.[*].sujetThese").value(hasItem(DEFAULT_SUJET_THESE.toString())));
    }
    
    @Test
    @Transactional
    public void getLastTheses() throws Exception {
        // Initialize the database
        lastThesesRepository.saveAndFlush(lastTheses);

        // Get the lastTheses
        restLastThesesMockMvc.perform(get("/api/last-theses/{id}", lastTheses.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lastTheses.getId().intValue()))
            .andExpect(jsonPath("$.anneeUniversitaire").value(DEFAULT_ANNEE_UNIVERSITAIRE.toString()))
            .andExpect(jsonPath("$.etablissement").value(DEFAULT_ETABLISSEMENT.toString()))
            .andExpect(jsonPath("$.sujetThese").value(DEFAULT_SUJET_THESE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLastTheses() throws Exception {
        // Get the lastTheses
        restLastThesesMockMvc.perform(get("/api/last-theses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLastTheses() throws Exception {
        // Initialize the database
        lastThesesRepository.saveAndFlush(lastTheses);

        int databaseSizeBeforeUpdate = lastThesesRepository.findAll().size();

        // Update the lastTheses
        LastTheses updatedLastTheses = lastThesesRepository.findById(lastTheses.getId()).get();
        // Disconnect from session so that the updates on updatedLastTheses are not directly saved in db
        em.detach(updatedLastTheses);
        updatedLastTheses
            .anneeUniversitaire(UPDATED_ANNEE_UNIVERSITAIRE)
            .etablissement(UPDATED_ETABLISSEMENT)
            .sujetThese(UPDATED_SUJET_THESE);
        LastThesesDTO lastThesesDTO = lastThesesMapper.toDto(updatedLastTheses);

        restLastThesesMockMvc.perform(put("/api/last-theses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lastThesesDTO)))
            .andExpect(status().isOk());

        // Validate the LastTheses in the database
        List<LastTheses> lastThesesList = lastThesesRepository.findAll();
        assertThat(lastThesesList).hasSize(databaseSizeBeforeUpdate);
        LastTheses testLastTheses = lastThesesList.get(lastThesesList.size() - 1);
        assertThat(testLastTheses.getAnneeUniversitaire()).isEqualTo(UPDATED_ANNEE_UNIVERSITAIRE);
        assertThat(testLastTheses.getEtablissement()).isEqualTo(UPDATED_ETABLISSEMENT);
        assertThat(testLastTheses.getSujetThese()).isEqualTo(UPDATED_SUJET_THESE);
    }

    @Test
    @Transactional
    public void updateNonExistingLastTheses() throws Exception {
        int databaseSizeBeforeUpdate = lastThesesRepository.findAll().size();

        // Create the LastTheses
        LastThesesDTO lastThesesDTO = lastThesesMapper.toDto(lastTheses);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLastThesesMockMvc.perform(put("/api/last-theses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lastThesesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LastTheses in the database
        List<LastTheses> lastThesesList = lastThesesRepository.findAll();
        assertThat(lastThesesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLastTheses() throws Exception {
        // Initialize the database
        lastThesesRepository.saveAndFlush(lastTheses);

        int databaseSizeBeforeDelete = lastThesesRepository.findAll().size();

        // Delete the lastTheses
        restLastThesesMockMvc.perform(delete("/api/last-theses/{id}", lastTheses.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LastTheses> lastThesesList = lastThesesRepository.findAll();
        assertThat(lastThesesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LastTheses.class);
        LastTheses lastTheses1 = new LastTheses();
        lastTheses1.setId(1L);
        LastTheses lastTheses2 = new LastTheses();
        lastTheses2.setId(lastTheses1.getId());
        assertThat(lastTheses1).isEqualTo(lastTheses2);
        lastTheses2.setId(2L);
        assertThat(lastTheses1).isNotEqualTo(lastTheses2);
        lastTheses1.setId(null);
        assertThat(lastTheses1).isNotEqualTo(lastTheses2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LastThesesDTO.class);
        LastThesesDTO lastThesesDTO1 = new LastThesesDTO();
        lastThesesDTO1.setId(1L);
        LastThesesDTO lastThesesDTO2 = new LastThesesDTO();
        assertThat(lastThesesDTO1).isNotEqualTo(lastThesesDTO2);
        lastThesesDTO2.setId(lastThesesDTO1.getId());
        assertThat(lastThesesDTO1).isEqualTo(lastThesesDTO2);
        lastThesesDTO2.setId(2L);
        assertThat(lastThesesDTO1).isNotEqualTo(lastThesesDTO2);
        lastThesesDTO1.setId(null);
        assertThat(lastThesesDTO1).isNotEqualTo(lastThesesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(lastThesesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(lastThesesMapper.fromId(null)).isNull();
    }
}
