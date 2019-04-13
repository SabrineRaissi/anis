package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.PossibleValue;
import com.mycompany.myapp.repository.PossibleValueRepository;
import com.mycompany.myapp.service.PossibleValueService;
import com.mycompany.myapp.service.dto.PossibleValueDTO;
import com.mycompany.myapp.service.mapper.PossibleValueMapper;
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
 * Test class for the PossibleValueResource REST controller.
 *
 * @see PossibleValueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class PossibleValueResourceIntTest {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private PossibleValueRepository possibleValueRepository;

    @Autowired
    private PossibleValueMapper possibleValueMapper;

    @Autowired
    private PossibleValueService possibleValueService;

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

    private MockMvc restPossibleValueMockMvc;

    private PossibleValue possibleValue;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PossibleValueResource possibleValueResource = new PossibleValueResource(possibleValueService);
        this.restPossibleValueMockMvc = MockMvcBuilders.standaloneSetup(possibleValueResource)
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
    public static PossibleValue createEntity(EntityManager em) {
        PossibleValue possibleValue = new PossibleValue()
            .value(DEFAULT_VALUE);
        return possibleValue;
    }

    @Before
    public void initTest() {
        possibleValue = createEntity(em);
    }

    @Test
    @Transactional
    public void createPossibleValue() throws Exception {
        int databaseSizeBeforeCreate = possibleValueRepository.findAll().size();

        // Create the PossibleValue
        PossibleValueDTO possibleValueDTO = possibleValueMapper.toDto(possibleValue);
        restPossibleValueMockMvc.perform(post("/api/possible-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(possibleValueDTO)))
            .andExpect(status().isCreated());

        // Validate the PossibleValue in the database
        List<PossibleValue> possibleValueList = possibleValueRepository.findAll();
        assertThat(possibleValueList).hasSize(databaseSizeBeforeCreate + 1);
        PossibleValue testPossibleValue = possibleValueList.get(possibleValueList.size() - 1);
        assertThat(testPossibleValue.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createPossibleValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = possibleValueRepository.findAll().size();

        // Create the PossibleValue with an existing ID
        possibleValue.setId(1L);
        PossibleValueDTO possibleValueDTO = possibleValueMapper.toDto(possibleValue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPossibleValueMockMvc.perform(post("/api/possible-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(possibleValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PossibleValue in the database
        List<PossibleValue> possibleValueList = possibleValueRepository.findAll();
        assertThat(possibleValueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPossibleValues() throws Exception {
        // Initialize the database
        possibleValueRepository.saveAndFlush(possibleValue);

        // Get all the possibleValueList
        restPossibleValueMockMvc.perform(get("/api/possible-values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(possibleValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getPossibleValue() throws Exception {
        // Initialize the database
        possibleValueRepository.saveAndFlush(possibleValue);

        // Get the possibleValue
        restPossibleValueMockMvc.perform(get("/api/possible-values/{id}", possibleValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(possibleValue.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPossibleValue() throws Exception {
        // Get the possibleValue
        restPossibleValueMockMvc.perform(get("/api/possible-values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePossibleValue() throws Exception {
        // Initialize the database
        possibleValueRepository.saveAndFlush(possibleValue);

        int databaseSizeBeforeUpdate = possibleValueRepository.findAll().size();

        // Update the possibleValue
        PossibleValue updatedPossibleValue = possibleValueRepository.findById(possibleValue.getId()).get();
        // Disconnect from session so that the updates on updatedPossibleValue are not directly saved in db
        em.detach(updatedPossibleValue);
        updatedPossibleValue
            .value(UPDATED_VALUE);
        PossibleValueDTO possibleValueDTO = possibleValueMapper.toDto(updatedPossibleValue);

        restPossibleValueMockMvc.perform(put("/api/possible-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(possibleValueDTO)))
            .andExpect(status().isOk());

        // Validate the PossibleValue in the database
        List<PossibleValue> possibleValueList = possibleValueRepository.findAll();
        assertThat(possibleValueList).hasSize(databaseSizeBeforeUpdate);
        PossibleValue testPossibleValue = possibleValueList.get(possibleValueList.size() - 1);
        assertThat(testPossibleValue.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingPossibleValue() throws Exception {
        int databaseSizeBeforeUpdate = possibleValueRepository.findAll().size();

        // Create the PossibleValue
        PossibleValueDTO possibleValueDTO = possibleValueMapper.toDto(possibleValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPossibleValueMockMvc.perform(put("/api/possible-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(possibleValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PossibleValue in the database
        List<PossibleValue> possibleValueList = possibleValueRepository.findAll();
        assertThat(possibleValueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePossibleValue() throws Exception {
        // Initialize the database
        possibleValueRepository.saveAndFlush(possibleValue);

        int databaseSizeBeforeDelete = possibleValueRepository.findAll().size();

        // Delete the possibleValue
        restPossibleValueMockMvc.perform(delete("/api/possible-values/{id}", possibleValue.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PossibleValue> possibleValueList = possibleValueRepository.findAll();
        assertThat(possibleValueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PossibleValue.class);
        PossibleValue possibleValue1 = new PossibleValue();
        possibleValue1.setId(1L);
        PossibleValue possibleValue2 = new PossibleValue();
        possibleValue2.setId(possibleValue1.getId());
        assertThat(possibleValue1).isEqualTo(possibleValue2);
        possibleValue2.setId(2L);
        assertThat(possibleValue1).isNotEqualTo(possibleValue2);
        possibleValue1.setId(null);
        assertThat(possibleValue1).isNotEqualTo(possibleValue2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PossibleValueDTO.class);
        PossibleValueDTO possibleValueDTO1 = new PossibleValueDTO();
        possibleValueDTO1.setId(1L);
        PossibleValueDTO possibleValueDTO2 = new PossibleValueDTO();
        assertThat(possibleValueDTO1).isNotEqualTo(possibleValueDTO2);
        possibleValueDTO2.setId(possibleValueDTO1.getId());
        assertThat(possibleValueDTO1).isEqualTo(possibleValueDTO2);
        possibleValueDTO2.setId(2L);
        assertThat(possibleValueDTO1).isNotEqualTo(possibleValueDTO2);
        possibleValueDTO1.setId(null);
        assertThat(possibleValueDTO1).isNotEqualTo(possibleValueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(possibleValueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(possibleValueMapper.fromId(null)).isNull();
    }
}
