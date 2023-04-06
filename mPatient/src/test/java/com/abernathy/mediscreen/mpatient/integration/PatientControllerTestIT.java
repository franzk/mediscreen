package com.abernathy.mediscreen.mpatient.integration;

import com.abernathy.mediscreen.mpatient.GenerateTestData;
import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mdto.model.PatientDto;
import com.abernathy.mediscreen.mpatient.service.PatientServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@PropertySource("classpath:messages.properties")
class PatientControllerTestIT {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private static final String POPULATE_TEST_DB_SQL = "classpath:populate_test_db.sql";
    private static final int TEST_RECORDS_COUNT = 10;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PatientServiceImpl patientService;

    final ObjectMapper mapper = new ObjectMapper();

    @Value("${patient.error.invaliddateformat}")
    private String invalidDateFormatMessage;

    @Value("${patient.error.patientnotfound}")
    private String patientNotFoundErrorMessage;

    //@BeforeAll
    void setUp() {
        // Make that Jackson handle LocalDateTime Type
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @DisplayName("POST : /patient/add ")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void addTestIT() throws Exception {
        // Arrange
        String urlData = "family=TestNone&given=Test&dob=1966-12-31&sex=F&address=1 Brookside St&phone=100-222-3333";

        // Act
        mockMvc.perform(post("/patient/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(urlData))
                        .andExpect(status().isOk());

        // Arrange
        List<Patient> allPatients = patientService.getAll();
        Patient resultExpected = allPatients.get(allPatients.size() - 1);
        assertThat(resultExpected.getLastName()).isEqualTo("TestNone");
        assertThat(resultExpected.getFirstName()).isEqualTo("Test");
        assertThat(resultExpected.getBirthdate()).isEqualTo(LocalDate.of(1966, 12, 31));
        assertThat(resultExpected.getSex()).isEqualTo("F");
        assertThat(resultExpected.getAddress()).isEqualTo("1 Brookside St");
        assertThat(resultExpected.getPhone()).isEqualTo("100-222-3333");
    }

    @DisplayName("POST : /patient/add with Date Format Exception")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void addWithDateFormatExceptionTestIT() throws Exception {
        // Arrange
        String urlData = "family=TestNone&given=Test&dob=1966-12-32&sex=F&address=1 Brookside St&phone=100-222-3333";

        // Act + Assert
        mockMvc.perform(post("/patient/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(urlData))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(invalidDateFormatMessage));
    }

    @DisplayName("GET : /patient/ (getAll)")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void getAllIT() throws Exception {
        // Arrange
        Patient testPatient = GenerateTestData.patient();
        patientService.add(testPatient);
        // Act
        ResultActions resultActions = mockMvc
                .perform(get("/patient/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(testPatient.getLastName())));
        // Assert
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Patient> resultData = mapper.readValue(contentAsString, new TypeReference<>() {});
        assertThat(resultData).hasSize(TEST_RECORDS_COUNT + 1);
    }

    @DisplayName("GET : /patient/{id} ")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void getByIdTestIT() throws Exception {
        // Arrange
        Patient testPatient = GenerateTestData.patient();
        int patientId = patientService.add(testPatient).getId();
        // Act
        ResultActions resultActions = mockMvc
                .perform(get("/patient/{id}", patientId))
                .andExpect(status().isOk());
        // Assert
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Patient resultData = mapper.readValue(contentAsString, Patient.class);
        assertThat(resultData.getLastName()).isEqualTo(testPatient.getLastName());
    }

    @DisplayName("GET : /patient/{id} with PatientNotFoundException")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
   // @Test
    void getByIdWithPatientNotFoundExceptionTestIT() throws Exception {
        // Arrange
        int id = Integer.MAX_VALUE;
        // Act
        mockMvc.perform(get("/patient/{id}", id))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(patientNotFoundErrorMessage));
    }

    @DisplayName("PUT : /patient/udpate/ ")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void updateTestIT() throws Exception {
        // Arrange
        Patient testPatient = GenerateTestData.patient();
        Patient addedPatient = patientService.add(testPatient);

        PatientDto testDto = GenerateTestData.patientDto(1999, 12, 12);

        testDto.setId(testPatient.getId());

        String requestJson = mapper.writeValueAsString(testDto);

        // Act
        mockMvc.perform(put("/patient/update").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk());
        // Assert
        Patient result = patientService.getById(addedPatient.getId());
        assertThat(result.getLastName()).isEqualTo(testDto.getLastName());
        assertThat(result.getFirstName()).isEqualTo(testDto.getFirstName());
        assertThat(result.getBirthdate()).isEqualTo(LocalDate.of(1999, 12, 12));
        assertThat(result.getSex()).isEqualTo(testDto.getSex());
        assertThat(result.getAddress()).isEqualTo(testDto.getAddress());
        assertThat(result.getPhone()).isEqualTo(testDto.getPhone());

    }

    @DisplayName("PUT : /patient/udpate/ with PatientNotFoundException")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void updateWithPatientNotFoundExceptionTestIT() throws Exception {
        // Arrange
        PatientDto testDto = GenerateTestData.patientDto();
        testDto.setId(Integer.MAX_VALUE);
        String requestJson = mapper.writeValueAsString(testDto);

        // Act + Assert
        mockMvc.perform(put("/patient/update").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(patientNotFoundErrorMessage));
    }


    @DisplayName("PUT : /patient/udpate/ with DateFormatException")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void updateWithDateFormatExceptionTestIT() throws Exception {
        // Arrange
        Patient testPatient = GenerateTestData.patient();

        PatientDto testDto = GenerateTestData.patientDto(1999, 12, 45); // 45/12/1999 is bad

        testDto.setId(testPatient.getId());

        String requestJson = mapper.writeValueAsString(testDto);

        // Act + Assert
        mockMvc.perform(put("/patient/update").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(invalidDateFormatMessage));

    }

    @DisplayName("DELETE : /patient/delete/{id} ")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void deleteByIdTestIT() throws Exception {
        // Arrange
        List<Patient> patients = patientService.getAll();
        int idToDelete = patients.get(new Random().nextInt(patients.size())).getId();
        // Act
        mockMvc.perform(delete("/patient/delete/{id}", idToDelete))
                .andExpect(status().isOk());
        // Assert
        assertThrows(PatientNotFoundException.class, () -> patientService.getById(idToDelete));
    }

    @DisplayName("DELETE : /patient/delete/{id} with not found error")
    @Sql(scripts = POPULATE_TEST_DB_SQL)
    //@Test
    void deleteByIdWithNotFoundExceptionTestIT() throws Exception {
        // Arrange
        int id = Integer.MAX_VALUE;
        // Act + Assert
        mockMvc.perform(delete("/patient/delete/{id}", id))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(patientNotFoundErrorMessage));
    }

}
