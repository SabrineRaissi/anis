package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.Doctorant;
import com.mycompany.myapp.repository.DoctorantRepository;
import com.mycompany.myapp.service.DoctorantService;
import com.mycompany.myapp.service.dto.DoctorantDTO;
import com.mycompany.myapp.service.mapper.DoctorantMapper;
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
import org.springframework.util.Base64Utils;
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

/**
 * Test class for the DoctorantResource REST controller.
 *
 * @see DoctorantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class DoctorantResourceIntTest {

    private static final String DEFAULT_NATIONALITE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_NISSANCE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_NISSANCE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_SEXE = "AAAAAAAAAA";
    private static final String UPDATED_SEXE = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT_CIVIL = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_CIVIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEUR = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEUR = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PROFILE_PIC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROFILE_PIC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROFILE_PIC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROFILE_PIC_CONTENT_TYPE = "image/png";

    @Autowired
    private DoctorantRepository doctorantRepository;

    @Autowired
    private DoctorantMapper doctorantMapper;

    @Autowired
    private DoctorantService doctorantService;

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

    private MockMvc restDoctorantMockMvc;

    private Doctorant doctorant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DoctorantResource doctorantResource = new DoctorantResource(doctorantService);
        this.restDoctorantMockMvc = MockMvcBuilders.standaloneSetup(doctorantResource)
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
    public static Doctorant createEntity(EntityManager em) {
        Doctorant doctorant = new Doctorant()
            .nationalite(DEFAULT_NATIONALITE)
            .dateNissance(DEFAULT_DATE_NISSANCE)
            .sexe(DEFAULT_SEXE)
            .etatCivil(DEFAULT_ETAT_CIVIL)
            .adresse(DEFAULT_ADRESSE)
            .profession(DEFAULT_PROFESSION)
            .employeur(DEFAULT_EMPLOYEUR)
            .profilePic(DEFAULT_PROFILE_PIC)
            .profilePicContentType(DEFAULT_PROFILE_PIC_CONTENT_TYPE);
        return doctorant;
    }

    @Before
    public void initTest() {
        doctorant = createEntity(em);
    }

    @Test
    @Transactional
    public void createDoctorant() throws Exception {
        int databaseSizeBeforeCreate = doctorantRepository.findAll().size();

        // Create the Doctorant
        DoctorantDTO doctorantDTO = doctorantMapper.toDto(doctorant);
        restDoctorantMockMvc.perform(post("/api/doctorants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctorantDTO)))
            .andExpect(status().isCreated());

        // Validate the Doctorant in the database
        List<Doctorant> doctorantList = doctorantRepository.findAll();
        assertThat(doctorantList).hasSize(databaseSizeBeforeCreate + 1);
        Doctorant testDoctorant = doctorantList.get(doctorantList.size() - 1);
        assertThat(testDoctorant.getNationalite()).isEqualTo(DEFAULT_NATIONALITE);
        assertThat(testDoctorant.getDateNissance()).isEqualTo(DEFAULT_DATE_NISSANCE);
        assertThat(testDoctorant.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testDoctorant.getEtatCivil()).isEqualTo(DEFAULT_ETAT_CIVIL);
        assertThat(testDoctorant.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testDoctorant.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testDoctorant.getEmployeur()).isEqualTo(DEFAULT_EMPLOYEUR);
        assertThat(testDoctorant.getProfilePic()).isEqualTo(DEFAULT_PROFILE_PIC);
        assertThat(testDoctorant.getProfilePicContentType()).isEqualTo(DEFAULT_PROFILE_PIC_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createDoctorantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = doctorantRepository.findAll().size();

        // Create the Doctorant with an existing ID
        doctorant.setId(1L);
        DoctorantDTO doctorantDTO = doctorantMapper.toDto(doctorant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoctorantMockMvc.perform(post("/api/doctorants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctorantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Doctorant in the database
        List<Doctorant> doctorantList = doctorantRepository.findAll();
        assertThat(doctorantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDoctorants() throws Exception {
        // Initialize the database
        doctorantRepository.saveAndFlush(doctorant);

        // Get all the doctorantList
        restDoctorantMockMvc.perform(get("/api/doctorants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doctorant.getId().intValue())))
            .andExpect(jsonPath("$.[*].nationalite").value(hasItem(DEFAULT_NATIONALITE.toString())))
            .andExpect(jsonPath("$.[*].dateNissance").value(hasItem(sameInstant(DEFAULT_DATE_NISSANCE))))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].etatCivil").value(hasItem(DEFAULT_ETAT_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION.toString())))
            .andExpect(jsonPath("$.[*].employeur").value(hasItem(DEFAULT_EMPLOYEUR.toString())))
            .andExpect(jsonPath("$.[*].profilePicContentType").value(hasItem(DEFAULT_PROFILE_PIC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].profilePic").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROFILE_PIC))));
    }
    
    @Test
    @Transactional
    public void getDoctorant() throws Exception {
        // Initialize the database
        doctorantRepository.saveAndFlush(doctorant);

        // Get the doctorant
        restDoctorantMockMvc.perform(get("/api/doctorants/{id}", doctorant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(doctorant.getId().intValue()))
            .andExpect(jsonPath("$.nationalite").value(DEFAULT_NATIONALITE.toString()))
            .andExpect(jsonPath("$.dateNissance").value(sameInstant(DEFAULT_DATE_NISSANCE)))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.etatCivil").value(DEFAULT_ETAT_CIVIL.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION.toString()))
            .andExpect(jsonPath("$.employeur").value(DEFAULT_EMPLOYEUR.toString()))
            .andExpect(jsonPath("$.profilePicContentType").value(DEFAULT_PROFILE_PIC_CONTENT_TYPE))
            .andExpect(jsonPath("$.profilePic").value(Base64Utils.encodeToString(DEFAULT_PROFILE_PIC)));
    }

    @Test
    @Transactional
    public void getNonExistingDoctorant() throws Exception {
        // Get the doctorant
        restDoctorantMockMvc.perform(get("/api/doctorants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDoctorant() throws Exception {
        // Initialize the database
        doctorantRepository.saveAndFlush(doctorant);

        int databaseSizeBeforeUpdate = doctorantRepository.findAll().size();

        // Update the doctorant
        Doctorant updatedDoctorant = doctorantRepository.findById(doctorant.getId()).get();
        // Disconnect from session so that the updates on updatedDoctorant are not directly saved in db
        em.detach(updatedDoctorant);
        updatedDoctorant
            .nationalite(UPDATED_NATIONALITE)
            .dateNissance(UPDATED_DATE_NISSANCE)
            .sexe(UPDATED_SEXE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .adresse(UPDATED_ADRESSE)
            .profession(UPDATED_PROFESSION)
            .employeur(UPDATED_EMPLOYEUR)
            .profilePic(UPDATED_PROFILE_PIC)
            .profilePicContentType(UPDATED_PROFILE_PIC_CONTENT_TYPE);
        DoctorantDTO doctorantDTO = doctorantMapper.toDto(updatedDoctorant);

        restDoctorantMockMvc.perform(put("/api/doctorants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctorantDTO)))
            .andExpect(status().isOk());

        // Validate the Doctorant in the database
        List<Doctorant> doctorantList = doctorantRepository.findAll();
        assertThat(doctorantList).hasSize(databaseSizeBeforeUpdate);
        Doctorant testDoctorant = doctorantList.get(doctorantList.size() - 1);
        assertThat(testDoctorant.getNationalite()).isEqualTo(UPDATED_NATIONALITE);
        assertThat(testDoctorant.getDateNissance()).isEqualTo(UPDATED_DATE_NISSANCE);
        assertThat(testDoctorant.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testDoctorant.getEtatCivil()).isEqualTo(UPDATED_ETAT_CIVIL);
        assertThat(testDoctorant.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testDoctorant.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testDoctorant.getEmployeur()).isEqualTo(UPDATED_EMPLOYEUR);
        assertThat(testDoctorant.getProfilePic()).isEqualTo(UPDATED_PROFILE_PIC);
        assertThat(testDoctorant.getProfilePicContentType()).isEqualTo(UPDATED_PROFILE_PIC_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingDoctorant() throws Exception {
        int databaseSizeBeforeUpdate = doctorantRepository.findAll().size();

        // Create the Doctorant
        DoctorantDTO doctorantDTO = doctorantMapper.toDto(doctorant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoctorantMockMvc.perform(put("/api/doctorants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctorantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Doctorant in the database
        List<Doctorant> doctorantList = doctorantRepository.findAll();
        assertThat(doctorantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDoctorant() throws Exception {
        // Initialize the database
        doctorantRepository.saveAndFlush(doctorant);

        int databaseSizeBeforeDelete = doctorantRepository.findAll().size();

        // Delete the doctorant
        restDoctorantMockMvc.perform(delete("/api/doctorants/{id}", doctorant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Doctorant> doctorantList = doctorantRepository.findAll();
        assertThat(doctorantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Doctorant.class);
        Doctorant doctorant1 = new Doctorant();
        doctorant1.setId(1L);
        Doctorant doctorant2 = new Doctorant();
        doctorant2.setId(doctorant1.getId());
        assertThat(doctorant1).isEqualTo(doctorant2);
        doctorant2.setId(2L);
        assertThat(doctorant1).isNotEqualTo(doctorant2);
        doctorant1.setId(null);
        assertThat(doctorant1).isNotEqualTo(doctorant2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoctorantDTO.class);
        DoctorantDTO doctorantDTO1 = new DoctorantDTO();
        doctorantDTO1.setId(1L);
        DoctorantDTO doctorantDTO2 = new DoctorantDTO();
        assertThat(doctorantDTO1).isNotEqualTo(doctorantDTO2);
        doctorantDTO2.setId(doctorantDTO1.getId());
        assertThat(doctorantDTO1).isEqualTo(doctorantDTO2);
        doctorantDTO2.setId(2L);
        assertThat(doctorantDTO1).isNotEqualTo(doctorantDTO2);
        doctorantDTO1.setId(null);
        assertThat(doctorantDTO1).isNotEqualTo(doctorantDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(doctorantMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(doctorantMapper.fromId(null)).isNull();
    }
}
