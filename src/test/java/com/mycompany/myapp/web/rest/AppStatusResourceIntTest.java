package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.AppStatus;
import com.mycompany.myapp.repository.AppStatusRepository;
import com.mycompany.myapp.service.AppStatusService;
import com.mycompany.myapp.service.dto.AppStatusDTO;
import com.mycompany.myapp.service.mapper.AppStatusMapper;
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
 * Test class for the AppStatusResource REST controller.
 *
 * @see AppStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class AppStatusResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    @Autowired
    private AppStatusRepository appStatusRepository;

    @Autowired
    private AppStatusMapper appStatusMapper;

    @Autowired
    private AppStatusService appStatusService;

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

    private MockMvc restAppStatusMockMvc;

    private AppStatus appStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppStatusResource appStatusResource = new AppStatusResource(appStatusService);
        this.restAppStatusMockMvc = MockMvcBuilders.standaloneSetup(appStatusResource)
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
    public static AppStatus createEntity(EntityManager em) {
        AppStatus appStatus = new AppStatus()
            .designation(DEFAULT_DESIGNATION)
            .code(DEFAULT_CODE);
        return appStatus;
    }

    @Before
    public void initTest() {
        appStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppStatus() throws Exception {
        int databaseSizeBeforeCreate = appStatusRepository.findAll().size();

        // Create the AppStatus
        AppStatusDTO appStatusDTO = appStatusMapper.toDto(appStatus);
        restAppStatusMockMvc.perform(post("/api/app-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the AppStatus in the database
        List<AppStatus> appStatusList = appStatusRepository.findAll();
        assertThat(appStatusList).hasSize(databaseSizeBeforeCreate + 1);
        AppStatus testAppStatus = appStatusList.get(appStatusList.size() - 1);
        assertThat(testAppStatus.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testAppStatus.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createAppStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appStatusRepository.findAll().size();

        // Create the AppStatus with an existing ID
        appStatus.setId(1L);
        AppStatusDTO appStatusDTO = appStatusMapper.toDto(appStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppStatusMockMvc.perform(post("/api/app-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppStatus in the database
        List<AppStatus> appStatusList = appStatusRepository.findAll();
        assertThat(appStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAppStatuses() throws Exception {
        // Initialize the database
        appStatusRepository.saveAndFlush(appStatus);

        // Get all the appStatusList
        restAppStatusMockMvc.perform(get("/api/app-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getAppStatus() throws Exception {
        // Initialize the database
        appStatusRepository.saveAndFlush(appStatus);

        // Get the appStatus
        restAppStatusMockMvc.perform(get("/api/app-statuses/{id}", appStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(appStatus.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingAppStatus() throws Exception {
        // Get the appStatus
        restAppStatusMockMvc.perform(get("/api/app-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppStatus() throws Exception {
        // Initialize the database
        appStatusRepository.saveAndFlush(appStatus);

        int databaseSizeBeforeUpdate = appStatusRepository.findAll().size();

        // Update the appStatus
        AppStatus updatedAppStatus = appStatusRepository.findById(appStatus.getId()).get();
        // Disconnect from session so that the updates on updatedAppStatus are not directly saved in db
        em.detach(updatedAppStatus);
        updatedAppStatus
            .designation(UPDATED_DESIGNATION)
            .code(UPDATED_CODE);
        AppStatusDTO appStatusDTO = appStatusMapper.toDto(updatedAppStatus);

        restAppStatusMockMvc.perform(put("/api/app-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appStatusDTO)))
            .andExpect(status().isOk());

        // Validate the AppStatus in the database
        List<AppStatus> appStatusList = appStatusRepository.findAll();
        assertThat(appStatusList).hasSize(databaseSizeBeforeUpdate);
        AppStatus testAppStatus = appStatusList.get(appStatusList.size() - 1);
        assertThat(testAppStatus.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testAppStatus.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingAppStatus() throws Exception {
        int databaseSizeBeforeUpdate = appStatusRepository.findAll().size();

        // Create the AppStatus
        AppStatusDTO appStatusDTO = appStatusMapper.toDto(appStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppStatusMockMvc.perform(put("/api/app-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppStatus in the database
        List<AppStatus> appStatusList = appStatusRepository.findAll();
        assertThat(appStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppStatus() throws Exception {
        // Initialize the database
        appStatusRepository.saveAndFlush(appStatus);

        int databaseSizeBeforeDelete = appStatusRepository.findAll().size();

        // Delete the appStatus
        restAppStatusMockMvc.perform(delete("/api/app-statuses/{id}", appStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AppStatus> appStatusList = appStatusRepository.findAll();
        assertThat(appStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppStatus.class);
        AppStatus appStatus1 = new AppStatus();
        appStatus1.setId(1L);
        AppStatus appStatus2 = new AppStatus();
        appStatus2.setId(appStatus1.getId());
        assertThat(appStatus1).isEqualTo(appStatus2);
        appStatus2.setId(2L);
        assertThat(appStatus1).isNotEqualTo(appStatus2);
        appStatus1.setId(null);
        assertThat(appStatus1).isNotEqualTo(appStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppStatusDTO.class);
        AppStatusDTO appStatusDTO1 = new AppStatusDTO();
        appStatusDTO1.setId(1L);
        AppStatusDTO appStatusDTO2 = new AppStatusDTO();
        assertThat(appStatusDTO1).isNotEqualTo(appStatusDTO2);
        appStatusDTO2.setId(appStatusDTO1.getId());
        assertThat(appStatusDTO1).isEqualTo(appStatusDTO2);
        appStatusDTO2.setId(2L);
        assertThat(appStatusDTO1).isNotEqualTo(appStatusDTO2);
        appStatusDTO1.setId(null);
        assertThat(appStatusDTO1).isNotEqualTo(appStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(appStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(appStatusMapper.fromId(null)).isNull();
    }
}
