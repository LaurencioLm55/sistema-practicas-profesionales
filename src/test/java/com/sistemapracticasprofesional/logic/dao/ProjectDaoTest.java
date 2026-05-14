package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectDaoTest {

    private static final int TEST_PROJECT_ID = 930001;
    private static final int TEST_ORGANIZATION_ID = 930101;
    private ProjectDao projectDao;
    private AffiliatedOrganizationDao affiliatedOrganizationDao;
    private int testProjectId;
    private int testOrganizationId;

    @BeforeEach
    public void setUp() {
        projectDao = new ProjectDao();
        affiliatedOrganizationDao = new AffiliatedOrganizationDao();
        testProjectId = TEST_PROJECT_ID;
        testOrganizationId = TEST_ORGANIZATION_ID;

        AffiliatedOrganizationDto existingOrganization = 
                affiliatedOrganizationDao.getAffiliatedOrganization(testOrganizationId);

        if (existingOrganization.getName() == null) {
            AffiliatedOrganizationDto organization = createAffiliatedOrganization();
            
            affiliatedOrganizationDao.insertOrganization(organization);
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            projectDao.deleteProject(testProjectId);
        } catch (DaoException daoException) {
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @Test
    public void testInsertProjectSuccessfully() {
        ProjectDto projectTest = createProjectDto("Test Project", "Test Description",
                "SCRUM");

        assertTrue(projectDao.insertProject(projectTest));
    }

    @Test
    public void testUpdateProjectSuccessfully() {
        ProjectDto projectTest = createProjectDto("Initial Project", "Initial Description",
                "Waterfall");
        projectDao.insertProject(projectTest);

        projectTest.setProjectName("Updated Project");
        
        boolean success = projectDao.updateProject(projectTest);
        
        assertTrue(success);
    }


    @Test
    public void testDeleteProjectSuccessfully() {
        ProjectDto projectTest = createProjectDto("Delete Project", "Description",
                "SCRUM");

        projectDao.insertProject(projectTest);

        boolean result = projectDao.deleteProject(testProjectId);

        assertNull(projectDao.getProjectById(testProjectId));
    }

    @Test
    public void testGetProjectSuccessfully() {
        ProjectDto projectTest = createProjectDto("Search Project", "Search Description",
                "XP");
        projectDao.insertProject(projectTest);

        ProjectDto success = projectDao.getProjectById(testProjectId);

        assertNotNull(success);
    }

    @Test
    public void testListProjectsSuccessfully() {
        ProjectDto projectTest = createProjectDto("List Project", "List Description",
                "SCRUM");
        projectDao.insertProject(projectTest);

        List<ProjectDto> listProjects = projectDao.getAllProjects();
        
        assertNotNull(listProjects);
    }

    @Test
    public void testInsertProjectUnsuccessfully() {
        ProjectDto projectTest = createProjectDto("List Project", "List Description",
                "SCRUM");
        projectTest.setLinkedOrganizationId(999999);
        
        assertThrows(DaoException.class, () -> projectDao.insertProject(projectTest));

    }

    private ProjectDto createProjectDto(String name, String description, String methodology) {
        ProjectDto project = new ProjectDto();
        project.setProjectId(testProjectId);
        project.setLinkedOrganizationId(testOrganizationId);
        project.setProjectName(name);
        project.setProjectDescription(description);
        project.setProjectMethodology(methodology);
        project.setProjectResources("Resources");
        project.setMidtermProjectObjectives("Midterm Objectives");
        project.setGeneralProjectObjectives("General Objective");
        project.setInmediateProjectObjectives("Immediate Objectives");
        project.setProjectResponsabilities("Responsibilities");
        project.setProjectAttendantName("Project Attendant");
        project.setProjectAttendantEmail("attendant@example.com");
        project.setProjectAttendantPosition("Monday");

        return project;
    }

    private AffiliatedOrganizationDto createAffiliatedOrganization() {
        AffiliatedOrganizationDto organization = new AffiliatedOrganizationDto();
        organization.setIdOrganization(String.valueOf(testOrganizationId));
        organization.setName("Project Test Organization");
        organization.setAddress("Test Address");
        organization.setSector("Technology");
        organization.setCity("Xalapa");
        organization.setState("Veracruz");
        organization.setPhoneNumber("2281234567");
        organization.setEmail("project.organization@example.com");

        return organization;
    }


}
