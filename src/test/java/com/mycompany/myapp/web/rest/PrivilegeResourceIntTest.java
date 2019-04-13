package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterrtestApp;

import com.mycompany.myapp.domain.Privilege;
import com.mycompany.myapp.repository.PrivilegeRepository;
import com.mycompany.myapp.service.PrivilegeService;
import com.mycompany.myapp.service.dto.PrivilegeDTO;
import com.mycompany.myapp.service.mapper.PrivilegeMapper;
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
 * Test class for the PrivilegeResource REST controller.
 *
 * @see PrivilegeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterrtestApp.class)
public class PrivilegeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private PrivilegeService privilegeService;

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

    private MockMvc restPrivilegeMockMvc;

    private Privilege privilege;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PrivilegeResource privilegeResource = new PrivilegeResource(privilegeService);
        this.restPrivilegeMockMvc = MockMvcBuilders.standaloneSetup(privilegeResource)
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
    public static Privilege createEntity(EntityManager em) {
        Privilege privilege = new Privilege()
            .name(DEFAULT_NAME);
        return privilege;
    }

    @Before
    public void initTest() {
        privilege = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrivilege() throws Exception {
        int databaseSizeBeforeCreate = privilegeRepository.findAll().size();

        // Create the Privilege
        PrivilegeDTO privilegeDTO = privilegeMapper.toDto(privilege);
        restPrivilegeMockMvc.perform(post("/api/privileges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(privilegeDTO)))
            .andExpect(status().isCreated());

        // Validate the Privilege in the database
        List<Privilege> privilegeList = privilegeRepository.findAll();
        assertThat(privilegeList).hasSize(databaseSizeBeforeCreate + 1);
        Privilege testPrivilege = privilegeList.get(privilegeList.size() - 1);
        assertThat(testPrivilege.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createPrivilegeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = privilegeRepository.findAll().size();

        // Create the Privilege with an existing ID
        privilege.setId(1L);
        PrivilegeDTO privilegeDTO = privilegeMapper.toDto(privilege);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrivilegeMockMvc.perform(post("/api/privileges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(privilegeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Privilege in the database
        List<Privilege> privilegeList = privilegeRepository.findAll();
        assertThat(privilegeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPrivileges() throws Exception {
        // Initialize the database
        privilegeRepository.saveAndFlush(privilege);

        // Get all the privilegeList
        restPrivilegeMockMvc.perform(get("/api/privileges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(privilege.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getPrivilege() throws Exception {
        // Initialize the database
        privilegeRepository.saveAndFlush(privilege);

        // Get the privilege
        restPrivilegeMockMvc.perform(get("/api/privileges/{id}", privilege.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(privilege.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPrivilege() throws Exception {
        // Get the privilege
        restPrivilegeMockMvc.perform(get("/api/privileges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrivilege() throws Exception {
        // Initialize the database
        privilegeRepository.saveAndFlush(privilege);

        int databaseSizeBeforeUpdate = privilegeRepository.findAll().size();

        // Update the privilege
        Privilege updatedPrivilege = privilegeRepository.findById(privilege.getId()).get();
        // Disconnect from session so that the updates on updatedPrivilege are not directly saved in db
        em.detach(updatedPrivilege);
        updatedPrivilege
            .name(UPDATED_NAME);
        PrivilegeDTO privilegeDTO = privilegeMapper.toDto(updatedPrivilege);

        restPrivilegeMockMvc.perform(put("/api/privileges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(privilegeDTO)))
            .andExpect(status().isOk());

        // Validate the Privilege in the database
        List<Privilege> privilegeList = privilegeRepository.findAll();
        assertThat(privilegeList).hasSize(databaseSizeBeforeUpdate);
        Privilege testPrivilege = privilegeList.get(privilegeList.size() - 1);
        assertThat(testPrivilege.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPrivilege() throws Exception {
        int databaseSizeBeforeUpdate = privilegeRepository.findAll().size();

        // Create the Privilege
        PrivilegeDTO privilegeDTO = privilegeMapper.toDto(privilege);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrivilegeMockMvc.perform(put("/api/privileges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(privilegeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Privilege in the database
        List<Privilege> privilegeList = privilegeRepository.findAll();
        assertThat(privilegeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrivilege() throws Exception {
        // Initialize the database
        privilegeRepository.saveAndFlush(privilege);

        int databaseSizeBeforeDelete = privilegeRepository.findAll().size();

        // Delete the privilege
        restPrivilegeMockMvc.perform(delete("/api/privileges/{id}", privilege.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Privilege> privilegeList = privilegeRepository.findAll();
        assertThat(privilegeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Privilege.class);
        Privilege privilege1 = new Privilege();
        privilege1.setId(1L);
        Privilege privilege2 = new Privilege();
        privilege2.setId(privilege1.getId());
        assertThat(privilege1).isEqualTo(privilege2);
        privilege2.setId(2L);
        assertThat(privilege1).isNotEqualTo(privilege2);
        privilege1.setId(null);
        assertThat(privilege1).isNotEqualTo(privilege2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrivilegeDTO.class);
        PrivilegeDTO privilegeDTO1 = new PrivilegeDTO();
        privilegeDTO1.setId(1L);
        PrivilegeDTO privilegeDTO2 = new PrivilegeDTO();
        assertThat(privilegeDTO1).isNotEqualTo(privilegeDTO2);
        privilegeDTO2.setId(privilegeDTO1.getId());
        assertThat(privilegeDTO1).isEqualTo(privilegeDTO2);
        privilegeDTO2.setId(2L);
        assertThat(privilegeDTO1).isNotEqualTo(privilegeDTO2);
        privilegeDTO1.setId(null);
        assertThat(privilegeDTO1).isNotEqualTo(privilegeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(privilegeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(privilegeMapper.fromId(null)).isNull();
    }
}
