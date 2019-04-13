package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.EtudeUniversitaire;
import com.mycompany.myapp.repository.EtudeUniversitaireRepository;
import com.mycompany.myapp.service.EtudeUniversitaireService;
import com.mycompany.myapp.service.dto.EtudeUniversitaireDTO;
import com.mycompany.myapp.service.mapper.EtudeUniversitaireMapper;
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
 * Test class for the EtudeUniversitaireResource REST controller.
 *
 * @see EtudeUniversitaireResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class EtudeUniversitaireResourceIntTest {

    private static final String DEFAULT_ANNEE_UNIVERSITAIRE = "AAAAAAAAAA";
    private static final String UPDATED_ANNEE_UNIVERSITAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_ETABLISSEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ETABLISSEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DIPLOME = "AAAAAAAAAA";
    private static final String UPDATED_DIPLOME = "BBBBBBBBBB";

    private static final String DEFAULT_NIVEAU_ETUDE = "AAAAAAAAAA";
    private static final String UPDATED_NIVEAU_ETUDE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARQUE = "AAAAAAAAAA";
    private static final String UPDATED_REMARQUE = "BBBBBBBBBB";

    @Autowired
    private EtudeUniversitaireRepository etudeUniversitaireRepository;

    @Autowired
    private EtudeUniversitaireMapper etudeUniversitaireMapper;

    @Autowired
    private EtudeUniversitaireService etudeUniversitaireService;

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

    private MockMvc restEtudeUniversitaireMockMvc;

    private EtudeUniversitaire etudeUniversitaire;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtudeUniversitaireResource etudeUniversitaireResource = new EtudeUniversitaireResource(etudeUniversitaireService);
        this.restEtudeUniversitaireMockMvc = MockMvcBuilders.standaloneSetup(etudeUniversitaireResource)
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
    public static EtudeUniversitaire createEntity(EntityManager em) {
        EtudeUniversitaire etudeUniversitaire = new EtudeUniversitaire()
            .anneeUniversitaire(DEFAULT_ANNEE_UNIVERSITAIRE)
            .etablissement(DEFAULT_ETABLISSEMENT)
            .diplome(DEFAULT_DIPLOME)
            .niveauEtude(DEFAULT_NIVEAU_ETUDE)
            .remarque(DEFAULT_REMARQUE);
        return etudeUniversitaire;
    }

    @Before
    public void initTest() {
        etudeUniversitaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtudeUniversitaire() throws Exception {
        int databaseSizeBeforeCreate = etudeUniversitaireRepository.findAll().size();

        // Create the EtudeUniversitaire
        EtudeUniversitaireDTO etudeUniversitaireDTO = etudeUniversitaireMapper.toDto(etudeUniversitaire);
        restEtudeUniversitaireMockMvc.perform(post("/api/etude-universitaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeUniversitaireDTO)))
            .andExpect(status().isCreated());

        // Validate the EtudeUniversitaire in the database
        List<EtudeUniversitaire> etudeUniversitaireList = etudeUniversitaireRepository.findAll();
        assertThat(etudeUniversitaireList).hasSize(databaseSizeBeforeCreate + 1);
        EtudeUniversitaire testEtudeUniversitaire = etudeUniversitaireList.get(etudeUniversitaireList.size() - 1);
        assertThat(testEtudeUniversitaire.getAnneeUniversitaire()).isEqualTo(DEFAULT_ANNEE_UNIVERSITAIRE);
        assertThat(testEtudeUniversitaire.getEtablissement()).isEqualTo(DEFAULT_ETABLISSEMENT);
        assertThat(testEtudeUniversitaire.getDiplome()).isEqualTo(DEFAULT_DIPLOME);
        assertThat(testEtudeUniversitaire.getNiveauEtude()).isEqualTo(DEFAULT_NIVEAU_ETUDE);
        assertThat(testEtudeUniversitaire.getRemarque()).isEqualTo(DEFAULT_REMARQUE);
    }

    @Test
    @Transactional
    public void createEtudeUniversitaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etudeUniversitaireRepository.findAll().size();

        // Create the EtudeUniversitaire with an existing ID
        etudeUniversitaire.setId(1L);
        EtudeUniversitaireDTO etudeUniversitaireDTO = etudeUniversitaireMapper.toDto(etudeUniversitaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtudeUniversitaireMockMvc.perform(post("/api/etude-universitaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeUniversitaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtudeUniversitaire in the database
        List<EtudeUniversitaire> etudeUniversitaireList = etudeUniversitaireRepository.findAll();
        assertThat(etudeUniversitaireList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEtudeUniversitaires() throws Exception {
        // Initialize the database
        etudeUniversitaireRepository.saveAndFlush(etudeUniversitaire);

        // Get all the etudeUniversitaireList
        restEtudeUniversitaireMockMvc.perform(get("/api/etude-universitaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etudeUniversitaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].anneeUniversitaire").value(hasItem(DEFAULT_ANNEE_UNIVERSITAIRE.toString())))
            .andExpect(jsonPath("$.[*].etablissement").value(hasItem(DEFAULT_ETABLISSEMENT.toString())))
            .andExpect(jsonPath("$.[*].diplome").value(hasItem(DEFAULT_DIPLOME.toString())))
            .andExpect(jsonPath("$.[*].niveauEtude").value(hasItem(DEFAULT_NIVEAU_ETUDE.toString())))
            .andExpect(jsonPath("$.[*].remarque").value(hasItem(DEFAULT_REMARQUE.toString())));
    }
    
    @Test
    @Transactional
    public void getEtudeUniversitaire() throws Exception {
        // Initialize the database
        etudeUniversitaireRepository.saveAndFlush(etudeUniversitaire);

        // Get the etudeUniversitaire
        restEtudeUniversitaireMockMvc.perform(get("/api/etude-universitaires/{id}", etudeUniversitaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etudeUniversitaire.getId().intValue()))
            .andExpect(jsonPath("$.anneeUniversitaire").value(DEFAULT_ANNEE_UNIVERSITAIRE.toString()))
            .andExpect(jsonPath("$.etablissement").value(DEFAULT_ETABLISSEMENT.toString()))
            .andExpect(jsonPath("$.diplome").value(DEFAULT_DIPLOME.toString()))
            .andExpect(jsonPath("$.niveauEtude").value(DEFAULT_NIVEAU_ETUDE.toString()))
            .andExpect(jsonPath("$.remarque").value(DEFAULT_REMARQUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEtudeUniversitaire() throws Exception {
        // Get the etudeUniversitaire
        restEtudeUniversitaireMockMvc.perform(get("/api/etude-universitaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtudeUniversitaire() throws Exception {
        // Initialize the database
        etudeUniversitaireRepository.saveAndFlush(etudeUniversitaire);

        int databaseSizeBeforeUpdate = etudeUniversitaireRepository.findAll().size();

        // Update the etudeUniversitaire
        EtudeUniversitaire updatedEtudeUniversitaire = etudeUniversitaireRepository.findById(etudeUniversitaire.getId()).get();
        // Disconnect from session so that the updates on updatedEtudeUniversitaire are not directly saved in db
        em.detach(updatedEtudeUniversitaire);
        updatedEtudeUniversitaire
            .anneeUniversitaire(UPDATED_ANNEE_UNIVERSITAIRE)
            .etablissement(UPDATED_ETABLISSEMENT)
            .diplome(UPDATED_DIPLOME)
            .niveauEtude(UPDATED_NIVEAU_ETUDE)
            .remarque(UPDATED_REMARQUE);
        EtudeUniversitaireDTO etudeUniversitaireDTO = etudeUniversitaireMapper.toDto(updatedEtudeUniversitaire);

        restEtudeUniversitaireMockMvc.perform(put("/api/etude-universitaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeUniversitaireDTO)))
            .andExpect(status().isOk());

        // Validate the EtudeUniversitaire in the database
        List<EtudeUniversitaire> etudeUniversitaireList = etudeUniversitaireRepository.findAll();
        assertThat(etudeUniversitaireList).hasSize(databaseSizeBeforeUpdate);
        EtudeUniversitaire testEtudeUniversitaire = etudeUniversitaireList.get(etudeUniversitaireList.size() - 1);
        assertThat(testEtudeUniversitaire.getAnneeUniversitaire()).isEqualTo(UPDATED_ANNEE_UNIVERSITAIRE);
        assertThat(testEtudeUniversitaire.getEtablissement()).isEqualTo(UPDATED_ETABLISSEMENT);
        assertThat(testEtudeUniversitaire.getDiplome()).isEqualTo(UPDATED_DIPLOME);
        assertThat(testEtudeUniversitaire.getNiveauEtude()).isEqualTo(UPDATED_NIVEAU_ETUDE);
        assertThat(testEtudeUniversitaire.getRemarque()).isEqualTo(UPDATED_REMARQUE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtudeUniversitaire() throws Exception {
        int databaseSizeBeforeUpdate = etudeUniversitaireRepository.findAll().size();

        // Create the EtudeUniversitaire
        EtudeUniversitaireDTO etudeUniversitaireDTO = etudeUniversitaireMapper.toDto(etudeUniversitaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtudeUniversitaireMockMvc.perform(put("/api/etude-universitaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etudeUniversitaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtudeUniversitaire in the database
        List<EtudeUniversitaire> etudeUniversitaireList = etudeUniversitaireRepository.findAll();
        assertThat(etudeUniversitaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtudeUniversitaire() throws Exception {
        // Initialize the database
        etudeUniversitaireRepository.saveAndFlush(etudeUniversitaire);

        int databaseSizeBeforeDelete = etudeUniversitaireRepository.findAll().size();

        // Delete the etudeUniversitaire
        restEtudeUniversitaireMockMvc.perform(delete("/api/etude-universitaires/{id}", etudeUniversitaire.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EtudeUniversitaire> etudeUniversitaireList = etudeUniversitaireRepository.findAll();
        assertThat(etudeUniversitaireList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtudeUniversitaire.class);
        EtudeUniversitaire etudeUniversitaire1 = new EtudeUniversitaire();
        etudeUniversitaire1.setId(1L);
        EtudeUniversitaire etudeUniversitaire2 = new EtudeUniversitaire();
        etudeUniversitaire2.setId(etudeUniversitaire1.getId());
        assertThat(etudeUniversitaire1).isEqualTo(etudeUniversitaire2);
        etudeUniversitaire2.setId(2L);
        assertThat(etudeUniversitaire1).isNotEqualTo(etudeUniversitaire2);
        etudeUniversitaire1.setId(null);
        assertThat(etudeUniversitaire1).isNotEqualTo(etudeUniversitaire2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtudeUniversitaireDTO.class);
        EtudeUniversitaireDTO etudeUniversitaireDTO1 = new EtudeUniversitaireDTO();
        etudeUniversitaireDTO1.setId(1L);
        EtudeUniversitaireDTO etudeUniversitaireDTO2 = new EtudeUniversitaireDTO();
        assertThat(etudeUniversitaireDTO1).isNotEqualTo(etudeUniversitaireDTO2);
        etudeUniversitaireDTO2.setId(etudeUniversitaireDTO1.getId());
        assertThat(etudeUniversitaireDTO1).isEqualTo(etudeUniversitaireDTO2);
        etudeUniversitaireDTO2.setId(2L);
        assertThat(etudeUniversitaireDTO1).isNotEqualTo(etudeUniversitaireDTO2);
        etudeUniversitaireDTO1.setId(null);
        assertThat(etudeUniversitaireDTO1).isNotEqualTo(etudeUniversitaireDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etudeUniversitaireMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etudeUniversitaireMapper.fromId(null)).isNull();
    }
}
