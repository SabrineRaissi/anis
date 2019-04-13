package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.Eligibility;
import com.mycompany.myapp.repository.EligibilityRepository;
import com.mycompany.myapp.service.EligibilityService;
import com.mycompany.myapp.service.dto.EligibilityDTO;
import com.mycompany.myapp.service.mapper.EligibilityMapper;
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
 * Test class for the EligibilityResource REST controller.
 *
 * @see EligibilityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class EligibilityResourceIntTest {

    private static final String DEFAULT_CRITERIA = "AAAAAAAAAA";
    private static final String UPDATED_CRITERIA = "BBBBBBBBBB";

    private static final Float DEFAULT_SCORE = 1F;
    private static final Float UPDATED_SCORE = 2F;

    @Autowired
    private EligibilityRepository eligibilityRepository;

    @Autowired
    private EligibilityMapper eligibilityMapper;

    @Autowired
    private EligibilityService eligibilityService;

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

    private MockMvc restEligibilityMockMvc;

    private Eligibility eligibility;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EligibilityResource eligibilityResource = new EligibilityResource(eligibilityService);
        this.restEligibilityMockMvc = MockMvcBuilders.standaloneSetup(eligibilityResource)
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
    public static Eligibility createEntity(EntityManager em) {
        Eligibility eligibility = new Eligibility()
            .criteria(DEFAULT_CRITERIA)
            .score(DEFAULT_SCORE);
        return eligibility;
    }

    @Before
    public void initTest() {
        eligibility = createEntity(em);
    }

    @Test
    @Transactional
    public void createEligibility() throws Exception {
        int databaseSizeBeforeCreate = eligibilityRepository.findAll().size();

        // Create the Eligibility
        EligibilityDTO eligibilityDTO = eligibilityMapper.toDto(eligibility);
        restEligibilityMockMvc.perform(post("/api/eligibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eligibilityDTO)))
            .andExpect(status().isCreated());

        // Validate the Eligibility in the database
        List<Eligibility> eligibilityList = eligibilityRepository.findAll();
        assertThat(eligibilityList).hasSize(databaseSizeBeforeCreate + 1);
        Eligibility testEligibility = eligibilityList.get(eligibilityList.size() - 1);
        assertThat(testEligibility.getCriteria()).isEqualTo(DEFAULT_CRITERIA);
        assertThat(testEligibility.getScore()).isEqualTo(DEFAULT_SCORE);
    }

    @Test
    @Transactional
    public void createEligibilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eligibilityRepository.findAll().size();

        // Create the Eligibility with an existing ID
        eligibility.setId(1L);
        EligibilityDTO eligibilityDTO = eligibilityMapper.toDto(eligibility);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEligibilityMockMvc.perform(post("/api/eligibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eligibilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Eligibility in the database
        List<Eligibility> eligibilityList = eligibilityRepository.findAll();
        assertThat(eligibilityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEligibilities() throws Exception {
        // Initialize the database
        eligibilityRepository.saveAndFlush(eligibility);

        // Get all the eligibilityList
        restEligibilityMockMvc.perform(get("/api/eligibilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eligibility.getId().intValue())))
            .andExpect(jsonPath("$.[*].criteria").value(hasItem(DEFAULT_CRITERIA.toString())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEligibility() throws Exception {
        // Initialize the database
        eligibilityRepository.saveAndFlush(eligibility);

        // Get the eligibility
        restEligibilityMockMvc.perform(get("/api/eligibilities/{id}", eligibility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eligibility.getId().intValue()))
            .andExpect(jsonPath("$.criteria").value(DEFAULT_CRITERIA.toString()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEligibility() throws Exception {
        // Get the eligibility
        restEligibilityMockMvc.perform(get("/api/eligibilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEligibility() throws Exception {
        // Initialize the database
        eligibilityRepository.saveAndFlush(eligibility);

        int databaseSizeBeforeUpdate = eligibilityRepository.findAll().size();

        // Update the eligibility
        Eligibility updatedEligibility = eligibilityRepository.findById(eligibility.getId()).get();
        // Disconnect from session so that the updates on updatedEligibility are not directly saved in db
        em.detach(updatedEligibility);
        updatedEligibility
            .criteria(UPDATED_CRITERIA)
            .score(UPDATED_SCORE);
        EligibilityDTO eligibilityDTO = eligibilityMapper.toDto(updatedEligibility);

        restEligibilityMockMvc.perform(put("/api/eligibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eligibilityDTO)))
            .andExpect(status().isOk());

        // Validate the Eligibility in the database
        List<Eligibility> eligibilityList = eligibilityRepository.findAll();
        assertThat(eligibilityList).hasSize(databaseSizeBeforeUpdate);
        Eligibility testEligibility = eligibilityList.get(eligibilityList.size() - 1);
        assertThat(testEligibility.getCriteria()).isEqualTo(UPDATED_CRITERIA);
        assertThat(testEligibility.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    public void updateNonExistingEligibility() throws Exception {
        int databaseSizeBeforeUpdate = eligibilityRepository.findAll().size();

        // Create the Eligibility
        EligibilityDTO eligibilityDTO = eligibilityMapper.toDto(eligibility);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEligibilityMockMvc.perform(put("/api/eligibilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eligibilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Eligibility in the database
        List<Eligibility> eligibilityList = eligibilityRepository.findAll();
        assertThat(eligibilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEligibility() throws Exception {
        // Initialize the database
        eligibilityRepository.saveAndFlush(eligibility);

        int databaseSizeBeforeDelete = eligibilityRepository.findAll().size();

        // Delete the eligibility
        restEligibilityMockMvc.perform(delete("/api/eligibilities/{id}", eligibility.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Eligibility> eligibilityList = eligibilityRepository.findAll();
        assertThat(eligibilityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Eligibility.class);
        Eligibility eligibility1 = new Eligibility();
        eligibility1.setId(1L);
        Eligibility eligibility2 = new Eligibility();
        eligibility2.setId(eligibility1.getId());
        assertThat(eligibility1).isEqualTo(eligibility2);
        eligibility2.setId(2L);
        assertThat(eligibility1).isNotEqualTo(eligibility2);
        eligibility1.setId(null);
        assertThat(eligibility1).isNotEqualTo(eligibility2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EligibilityDTO.class);
        EligibilityDTO eligibilityDTO1 = new EligibilityDTO();
        eligibilityDTO1.setId(1L);
        EligibilityDTO eligibilityDTO2 = new EligibilityDTO();
        assertThat(eligibilityDTO1).isNotEqualTo(eligibilityDTO2);
        eligibilityDTO2.setId(eligibilityDTO1.getId());
        assertThat(eligibilityDTO1).isEqualTo(eligibilityDTO2);
        eligibilityDTO2.setId(2L);
        assertThat(eligibilityDTO1).isNotEqualTo(eligibilityDTO2);
        eligibilityDTO1.setId(null);
        assertThat(eligibilityDTO1).isNotEqualTo(eligibilityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eligibilityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eligibilityMapper.fromId(null)).isNull();
    }
}
