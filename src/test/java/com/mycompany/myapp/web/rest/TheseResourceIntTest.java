package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.These;
import com.mycompany.myapp.repository.TheseRepository;
import com.mycompany.myapp.service.TheseService;
import com.mycompany.myapp.service.dto.TheseDTO;
import com.mycompany.myapp.service.mapper.TheseMapper;
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
 * Test class for the TheseResource REST controller.
 *
 * @see TheseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class TheseResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private TheseRepository theseRepository;

    @Autowired
    private TheseMapper theseMapper;

    @Autowired
    private TheseService theseService;

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

    private MockMvc restTheseMockMvc;

    private These these;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TheseResource theseResource = new TheseResource(theseService);
        this.restTheseMockMvc = MockMvcBuilders.standaloneSetup(theseResource)
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
    public static These createEntity(EntityManager em) {
        These these = new These()
            .designation(DEFAULT_DESIGNATION);
        return these;
    }

    @Before
    public void initTest() {
        these = createEntity(em);
    }

    @Test
    @Transactional
    public void createThese() throws Exception {
        int databaseSizeBeforeCreate = theseRepository.findAll().size();

        // Create the These
        TheseDTO theseDTO = theseMapper.toDto(these);
        restTheseMockMvc.perform(post("/api/these")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(theseDTO)))
            .andExpect(status().isCreated());

        // Validate the These in the database
        List<These> theseList = theseRepository.findAll();
        assertThat(theseList).hasSize(databaseSizeBeforeCreate + 1);
        These testThese = theseList.get(theseList.size() - 1);
        assertThat(testThese.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createTheseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = theseRepository.findAll().size();

        // Create the These with an existing ID
        these.setId(1L);
        TheseDTO theseDTO = theseMapper.toDto(these);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTheseMockMvc.perform(post("/api/these")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(theseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the These in the database
        List<These> theseList = theseRepository.findAll();
        assertThat(theseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllThese() throws Exception {
        // Initialize the database
        theseRepository.saveAndFlush(these);

        // Get all the theseList
        restTheseMockMvc.perform(get("/api/these?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(these.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getThese() throws Exception {
        // Initialize the database
        theseRepository.saveAndFlush(these);

        // Get the these
        restTheseMockMvc.perform(get("/api/these/{id}", these.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(these.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingThese() throws Exception {
        // Get the these
        restTheseMockMvc.perform(get("/api/these/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThese() throws Exception {
        // Initialize the database
        theseRepository.saveAndFlush(these);

        int databaseSizeBeforeUpdate = theseRepository.findAll().size();

        // Update the these
        These updatedThese = theseRepository.findById(these.getId()).get();
        // Disconnect from session so that the updates on updatedThese are not directly saved in db
        em.detach(updatedThese);
        updatedThese
            .designation(UPDATED_DESIGNATION);
        TheseDTO theseDTO = theseMapper.toDto(updatedThese);

        restTheseMockMvc.perform(put("/api/these")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(theseDTO)))
            .andExpect(status().isOk());

        // Validate the These in the database
        List<These> theseList = theseRepository.findAll();
        assertThat(theseList).hasSize(databaseSizeBeforeUpdate);
        These testThese = theseList.get(theseList.size() - 1);
        assertThat(testThese.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingThese() throws Exception {
        int databaseSizeBeforeUpdate = theseRepository.findAll().size();

        // Create the These
        TheseDTO theseDTO = theseMapper.toDto(these);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTheseMockMvc.perform(put("/api/these")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(theseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the These in the database
        List<These> theseList = theseRepository.findAll();
        assertThat(theseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteThese() throws Exception {
        // Initialize the database
        theseRepository.saveAndFlush(these);

        int databaseSizeBeforeDelete = theseRepository.findAll().size();

        // Delete the these
        restTheseMockMvc.perform(delete("/api/these/{id}", these.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<These> theseList = theseRepository.findAll();
        assertThat(theseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(These.class);
        These these1 = new These();
        these1.setId(1L);
        These these2 = new These();
        these2.setId(these1.getId());
        assertThat(these1).isEqualTo(these2);
        these2.setId(2L);
        assertThat(these1).isNotEqualTo(these2);
        these1.setId(null);
        assertThat(these1).isNotEqualTo(these2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TheseDTO.class);
        TheseDTO theseDTO1 = new TheseDTO();
        theseDTO1.setId(1L);
        TheseDTO theseDTO2 = new TheseDTO();
        assertThat(theseDTO1).isNotEqualTo(theseDTO2);
        theseDTO2.setId(theseDTO1.getId());
        assertThat(theseDTO1).isEqualTo(theseDTO2);
        theseDTO2.setId(2L);
        assertThat(theseDTO1).isNotEqualTo(theseDTO2);
        theseDTO1.setId(null);
        assertThat(theseDTO1).isNotEqualTo(theseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(theseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(theseMapper.fromId(null)).isNull();
    }
}
