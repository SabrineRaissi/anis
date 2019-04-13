package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.DeviceMetadata;
import com.mycompany.myapp.repository.DeviceMetadataRepository;
import com.mycompany.myapp.service.DeviceMetadataService;
import com.mycompany.myapp.service.dto.DeviceMetadataDTO;
import com.mycompany.myapp.service.mapper.DeviceMetadataMapper;
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
 * Test class for the DeviceMetadataResource REST controller.
 *
 * @see DeviceMetadataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class DeviceMetadataResourceIntTest {

    private static final Long DEFAULT_USER_ID_DEVICE = 1L;
    private static final Long UPDATED_USER_ID_DEVICE = 2L;

    private static final String DEFAULT_DEVICE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_LAST_LOGGED_IN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_LOGGED_IN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private DeviceMetadataRepository deviceMetadataRepository;

    @Autowired
    private DeviceMetadataMapper deviceMetadataMapper;

    @Autowired
    private DeviceMetadataService deviceMetadataService;

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

    private MockMvc restDeviceMetadataMockMvc;

    private DeviceMetadata deviceMetadata;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeviceMetadataResource deviceMetadataResource = new DeviceMetadataResource(deviceMetadataService);
        this.restDeviceMetadataMockMvc = MockMvcBuilders.standaloneSetup(deviceMetadataResource)
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
    public static DeviceMetadata createEntity(EntityManager em) {
        DeviceMetadata deviceMetadata = new DeviceMetadata()
            .userIdDevice(DEFAULT_USER_ID_DEVICE)
            .deviceDetails(DEFAULT_DEVICE_DETAILS)
            .location(DEFAULT_LOCATION)
            .lastLoggedIn(DEFAULT_LAST_LOGGED_IN);
        return deviceMetadata;
    }

    @Before
    public void initTest() {
        deviceMetadata = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeviceMetadata() throws Exception {
        int databaseSizeBeforeCreate = deviceMetadataRepository.findAll().size();

        // Create the DeviceMetadata
        DeviceMetadataDTO deviceMetadataDTO = deviceMetadataMapper.toDto(deviceMetadata);
        restDeviceMetadataMockMvc.perform(post("/api/device-metadata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceMetadataDTO)))
            .andExpect(status().isCreated());

        // Validate the DeviceMetadata in the database
        List<DeviceMetadata> deviceMetadataList = deviceMetadataRepository.findAll();
        assertThat(deviceMetadataList).hasSize(databaseSizeBeforeCreate + 1);
        DeviceMetadata testDeviceMetadata = deviceMetadataList.get(deviceMetadataList.size() - 1);
        assertThat(testDeviceMetadata.getUserIdDevice()).isEqualTo(DEFAULT_USER_ID_DEVICE);
        assertThat(testDeviceMetadata.getDeviceDetails()).isEqualTo(DEFAULT_DEVICE_DETAILS);
        assertThat(testDeviceMetadata.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testDeviceMetadata.getLastLoggedIn()).isEqualTo(DEFAULT_LAST_LOGGED_IN);
    }

    @Test
    @Transactional
    public void createDeviceMetadataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deviceMetadataRepository.findAll().size();

        // Create the DeviceMetadata with an existing ID
        deviceMetadata.setId(1L);
        DeviceMetadataDTO deviceMetadataDTO = deviceMetadataMapper.toDto(deviceMetadata);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviceMetadataMockMvc.perform(post("/api/device-metadata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceMetadataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceMetadata in the database
        List<DeviceMetadata> deviceMetadataList = deviceMetadataRepository.findAll();
        assertThat(deviceMetadataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDeviceMetadata() throws Exception {
        // Initialize the database
        deviceMetadataRepository.saveAndFlush(deviceMetadata);

        // Get all the deviceMetadataList
        restDeviceMetadataMockMvc.perform(get("/api/device-metadata?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deviceMetadata.getId().intValue())))
            .andExpect(jsonPath("$.[*].userIdDevice").value(hasItem(DEFAULT_USER_ID_DEVICE.intValue())))
            .andExpect(jsonPath("$.[*].deviceDetails").value(hasItem(DEFAULT_DEVICE_DETAILS.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].lastLoggedIn").value(hasItem(sameInstant(DEFAULT_LAST_LOGGED_IN))));
    }
    
    @Test
    @Transactional
    public void getDeviceMetadata() throws Exception {
        // Initialize the database
        deviceMetadataRepository.saveAndFlush(deviceMetadata);

        // Get the deviceMetadata
        restDeviceMetadataMockMvc.perform(get("/api/device-metadata/{id}", deviceMetadata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deviceMetadata.getId().intValue()))
            .andExpect(jsonPath("$.userIdDevice").value(DEFAULT_USER_ID_DEVICE.intValue()))
            .andExpect(jsonPath("$.deviceDetails").value(DEFAULT_DEVICE_DETAILS.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.lastLoggedIn").value(sameInstant(DEFAULT_LAST_LOGGED_IN)));
    }

    @Test
    @Transactional
    public void getNonExistingDeviceMetadata() throws Exception {
        // Get the deviceMetadata
        restDeviceMetadataMockMvc.perform(get("/api/device-metadata/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeviceMetadata() throws Exception {
        // Initialize the database
        deviceMetadataRepository.saveAndFlush(deviceMetadata);

        int databaseSizeBeforeUpdate = deviceMetadataRepository.findAll().size();

        // Update the deviceMetadata
        DeviceMetadata updatedDeviceMetadata = deviceMetadataRepository.findById(deviceMetadata.getId()).get();
        // Disconnect from session so that the updates on updatedDeviceMetadata are not directly saved in db
        em.detach(updatedDeviceMetadata);
        updatedDeviceMetadata
            .userIdDevice(UPDATED_USER_ID_DEVICE)
            .deviceDetails(UPDATED_DEVICE_DETAILS)
            .location(UPDATED_LOCATION)
            .lastLoggedIn(UPDATED_LAST_LOGGED_IN);
        DeviceMetadataDTO deviceMetadataDTO = deviceMetadataMapper.toDto(updatedDeviceMetadata);

        restDeviceMetadataMockMvc.perform(put("/api/device-metadata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceMetadataDTO)))
            .andExpect(status().isOk());

        // Validate the DeviceMetadata in the database
        List<DeviceMetadata> deviceMetadataList = deviceMetadataRepository.findAll();
        assertThat(deviceMetadataList).hasSize(databaseSizeBeforeUpdate);
        DeviceMetadata testDeviceMetadata = deviceMetadataList.get(deviceMetadataList.size() - 1);
        assertThat(testDeviceMetadata.getUserIdDevice()).isEqualTo(UPDATED_USER_ID_DEVICE);
        assertThat(testDeviceMetadata.getDeviceDetails()).isEqualTo(UPDATED_DEVICE_DETAILS);
        assertThat(testDeviceMetadata.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testDeviceMetadata.getLastLoggedIn()).isEqualTo(UPDATED_LAST_LOGGED_IN);
    }

    @Test
    @Transactional
    public void updateNonExistingDeviceMetadata() throws Exception {
        int databaseSizeBeforeUpdate = deviceMetadataRepository.findAll().size();

        // Create the DeviceMetadata
        DeviceMetadataDTO deviceMetadataDTO = deviceMetadataMapper.toDto(deviceMetadata);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviceMetadataMockMvc.perform(put("/api/device-metadata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceMetadataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceMetadata in the database
        List<DeviceMetadata> deviceMetadataList = deviceMetadataRepository.findAll();
        assertThat(deviceMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeviceMetadata() throws Exception {
        // Initialize the database
        deviceMetadataRepository.saveAndFlush(deviceMetadata);

        int databaseSizeBeforeDelete = deviceMetadataRepository.findAll().size();

        // Delete the deviceMetadata
        restDeviceMetadataMockMvc.perform(delete("/api/device-metadata/{id}", deviceMetadata.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DeviceMetadata> deviceMetadataList = deviceMetadataRepository.findAll();
        assertThat(deviceMetadataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviceMetadata.class);
        DeviceMetadata deviceMetadata1 = new DeviceMetadata();
        deviceMetadata1.setId(1L);
        DeviceMetadata deviceMetadata2 = new DeviceMetadata();
        deviceMetadata2.setId(deviceMetadata1.getId());
        assertThat(deviceMetadata1).isEqualTo(deviceMetadata2);
        deviceMetadata2.setId(2L);
        assertThat(deviceMetadata1).isNotEqualTo(deviceMetadata2);
        deviceMetadata1.setId(null);
        assertThat(deviceMetadata1).isNotEqualTo(deviceMetadata2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviceMetadataDTO.class);
        DeviceMetadataDTO deviceMetadataDTO1 = new DeviceMetadataDTO();
        deviceMetadataDTO1.setId(1L);
        DeviceMetadataDTO deviceMetadataDTO2 = new DeviceMetadataDTO();
        assertThat(deviceMetadataDTO1).isNotEqualTo(deviceMetadataDTO2);
        deviceMetadataDTO2.setId(deviceMetadataDTO1.getId());
        assertThat(deviceMetadataDTO1).isEqualTo(deviceMetadataDTO2);
        deviceMetadataDTO2.setId(2L);
        assertThat(deviceMetadataDTO1).isNotEqualTo(deviceMetadataDTO2);
        deviceMetadataDTO1.setId(null);
        assertThat(deviceMetadataDTO1).isNotEqualTo(deviceMetadataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(deviceMetadataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(deviceMetadataMapper.fromId(null)).isNull();
    }
}
