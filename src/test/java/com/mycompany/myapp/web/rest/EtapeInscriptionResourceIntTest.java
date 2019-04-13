package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.EtapeInscription;
import com.mycompany.myapp.repository.EtapeInscriptionRepository;
import com.mycompany.myapp.service.EtapeInscriptionService;
import com.mycompany.myapp.service.dto.EtapeInscriptionDTO;
import com.mycompany.myapp.service.mapper.EtapeInscriptionMapper;
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
 * Test class for the EtapeInscriptionResource REST controller.
 *
 * @see EtapeInscriptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class EtapeInscriptionResourceIntTest {

    private static final String DEFAULT_ETAPE = "AAAAAAAAAA";
    private static final String UPDATED_ETAPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EtapeInscriptionRepository etapeInscriptionRepository;

    @Autowired
    private EtapeInscriptionMapper etapeInscriptionMapper;

    @Autowired
    private EtapeInscriptionService etapeInscriptionService;

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

    private MockMvc restEtapeInscriptionMockMvc;

    private EtapeInscription etapeInscription;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtapeInscriptionResource etapeInscriptionResource = new EtapeInscriptionResource(etapeInscriptionService);
        this.restEtapeInscriptionMockMvc = MockMvcBuilders.standaloneSetup(etapeInscriptionResource)
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
    public static EtapeInscription createEntity(EntityManager em) {
        EtapeInscription etapeInscription = new EtapeInscription()
            .etape(DEFAULT_ETAPE)
            .description(DEFAULT_DESCRIPTION);
        return etapeInscription;
    }

    @Before
    public void initTest() {
        etapeInscription = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtapeInscription() throws Exception {
        int databaseSizeBeforeCreate = etapeInscriptionRepository.findAll().size();

        // Create the EtapeInscription
        EtapeInscriptionDTO etapeInscriptionDTO = etapeInscriptionMapper.toDto(etapeInscription);
        restEtapeInscriptionMockMvc.perform(post("/api/etape-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etapeInscriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the EtapeInscription in the database
        List<EtapeInscription> etapeInscriptionList = etapeInscriptionRepository.findAll();
        assertThat(etapeInscriptionList).hasSize(databaseSizeBeforeCreate + 1);
        EtapeInscription testEtapeInscription = etapeInscriptionList.get(etapeInscriptionList.size() - 1);
        assertThat(testEtapeInscription.getEtape()).isEqualTo(DEFAULT_ETAPE);
        assertThat(testEtapeInscription.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEtapeInscriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etapeInscriptionRepository.findAll().size();

        // Create the EtapeInscription with an existing ID
        etapeInscription.setId(1L);
        EtapeInscriptionDTO etapeInscriptionDTO = etapeInscriptionMapper.toDto(etapeInscription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtapeInscriptionMockMvc.perform(post("/api/etape-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etapeInscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtapeInscription in the database
        List<EtapeInscription> etapeInscriptionList = etapeInscriptionRepository.findAll();
        assertThat(etapeInscriptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEtapeInscriptions() throws Exception {
        // Initialize the database
        etapeInscriptionRepository.saveAndFlush(etapeInscription);

        // Get all the etapeInscriptionList
        restEtapeInscriptionMockMvc.perform(get("/api/etape-inscriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etapeInscription.getId().intValue())))
            .andExpect(jsonPath("$.[*].etape").value(hasItem(DEFAULT_ETAPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEtapeInscription() throws Exception {
        // Initialize the database
        etapeInscriptionRepository.saveAndFlush(etapeInscription);

        // Get the etapeInscription
        restEtapeInscriptionMockMvc.perform(get("/api/etape-inscriptions/{id}", etapeInscription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etapeInscription.getId().intValue()))
            .andExpect(jsonPath("$.etape").value(DEFAULT_ETAPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEtapeInscription() throws Exception {
        // Get the etapeInscription
        restEtapeInscriptionMockMvc.perform(get("/api/etape-inscriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtapeInscription() throws Exception {
        // Initialize the database
        etapeInscriptionRepository.saveAndFlush(etapeInscription);

        int databaseSizeBeforeUpdate = etapeInscriptionRepository.findAll().size();

        // Update the etapeInscription
        EtapeInscription updatedEtapeInscription = etapeInscriptionRepository.findById(etapeInscription.getId()).get();
        // Disconnect from session so that the updates on updatedEtapeInscription are not directly saved in db
        em.detach(updatedEtapeInscription);
        updatedEtapeInscription
            .etape(UPDATED_ETAPE)
            .description(UPDATED_DESCRIPTION);
        EtapeInscriptionDTO etapeInscriptionDTO = etapeInscriptionMapper.toDto(updatedEtapeInscription);

        restEtapeInscriptionMockMvc.perform(put("/api/etape-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etapeInscriptionDTO)))
            .andExpect(status().isOk());

        // Validate the EtapeInscription in the database
        List<EtapeInscription> etapeInscriptionList = etapeInscriptionRepository.findAll();
        assertThat(etapeInscriptionList).hasSize(databaseSizeBeforeUpdate);
        EtapeInscription testEtapeInscription = etapeInscriptionList.get(etapeInscriptionList.size() - 1);
        assertThat(testEtapeInscription.getEtape()).isEqualTo(UPDATED_ETAPE);
        assertThat(testEtapeInscription.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEtapeInscription() throws Exception {
        int databaseSizeBeforeUpdate = etapeInscriptionRepository.findAll().size();

        // Create the EtapeInscription
        EtapeInscriptionDTO etapeInscriptionDTO = etapeInscriptionMapper.toDto(etapeInscription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtapeInscriptionMockMvc.perform(put("/api/etape-inscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etapeInscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtapeInscription in the database
        List<EtapeInscription> etapeInscriptionList = etapeInscriptionRepository.findAll();
        assertThat(etapeInscriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtapeInscription() throws Exception {
        // Initialize the database
        etapeInscriptionRepository.saveAndFlush(etapeInscription);

        int databaseSizeBeforeDelete = etapeInscriptionRepository.findAll().size();

        // Delete the etapeInscription
        restEtapeInscriptionMockMvc.perform(delete("/api/etape-inscriptions/{id}", etapeInscription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EtapeInscription> etapeInscriptionList = etapeInscriptionRepository.findAll();
        assertThat(etapeInscriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtapeInscription.class);
        EtapeInscription etapeInscription1 = new EtapeInscription();
        etapeInscription1.setId(1L);
        EtapeInscription etapeInscription2 = new EtapeInscription();
        etapeInscription2.setId(etapeInscription1.getId());
        assertThat(etapeInscription1).isEqualTo(etapeInscription2);
        etapeInscription2.setId(2L);
        assertThat(etapeInscription1).isNotEqualTo(etapeInscription2);
        etapeInscription1.setId(null);
        assertThat(etapeInscription1).isNotEqualTo(etapeInscription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtapeInscriptionDTO.class);
        EtapeInscriptionDTO etapeInscriptionDTO1 = new EtapeInscriptionDTO();
        etapeInscriptionDTO1.setId(1L);
        EtapeInscriptionDTO etapeInscriptionDTO2 = new EtapeInscriptionDTO();
        assertThat(etapeInscriptionDTO1).isNotEqualTo(etapeInscriptionDTO2);
        etapeInscriptionDTO2.setId(etapeInscriptionDTO1.getId());
        assertThat(etapeInscriptionDTO1).isEqualTo(etapeInscriptionDTO2);
        etapeInscriptionDTO2.setId(2L);
        assertThat(etapeInscriptionDTO1).isNotEqualTo(etapeInscriptionDTO2);
        etapeInscriptionDTO1.setId(null);
        assertThat(etapeInscriptionDTO1).isNotEqualTo(etapeInscriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etapeInscriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etapeInscriptionMapper.fromId(null)).isNull();
    }
}
