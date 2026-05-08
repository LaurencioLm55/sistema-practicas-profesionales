package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinatorDaoTest {

    private static final int TEST_PERSONNEL_NUMBER = 920001;
    private static final int TEST_USER_ID = 920101;

    private CoordinatorDao coordinatorDao;
    private UserDao userDao;
    private int testPersonnelNumber;
    private int testUserId;

    @BeforeEach
    public void setUp() {
        coordinatorDao = new CoordinatorDao();
        userDao = new UserDao();
        testPersonnelNumber = TEST_PERSONNEL_NUMBER;
        testUserId = TEST_USER_ID;

        if (userDao.getUser(testUserId) == null) {
            UserDto user = new UserDto(testUserId, "Mtra. Estela Dominguez Romero", "S00983476", 1);
            userDao.insertUser(user);
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            coordinatorDao.deleteCoord(testUserId);
        } catch (DaoException daoException) {
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @Test
    public void testRegisterCoordinatorSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(testPersonnelNumber,
                testUserId, "Mtra. Laura Fernanda Castillo Perez", "Active",
                LocalDate.of(2026, 1, 15), LocalDate.of(2026, 12, 15));
        
        boolean success = coordinatorDao.insertCoordinator(coordinator);

        assertTrue(success);
    }

    @Test
    public void testUpdateCoordinatorSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(testPersonnelNumber,
                testUserId, "Mtro. Alejandro Ramirez Soto", "Active",
                LocalDate.of(2026, 2, 1), LocalDate.of(2026, 11, 30));
        coordinatorDao.insertCoordinator(coordinator);

        coordinator.setName("Mtro. Alejandro Ramirez Hernandez");
        
        boolean success = coordinatorDao.updateCoordinator(coordinator);
        
        assertTrue(success);
    }

    @Test
    public void testDeleteCoordSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(testPersonnelNumber,
                testUserId, "Dra. Mariana Torres Salazar", "Active",
                LocalDate.of(2026, 3, 1), LocalDate.of(2026, 12, 20));
        coordinatorDao.insertCoordinator(coordinator);
        
        boolean success = coordinatorDao.deleteCoord(testUserId);

        assertTrue(success);
    }

    @Test
    public void testGetCoordinatorByIdSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(testPersonnelNumber,
                testUserId, "Mtro. Ricardo Mendoza Cruz", "Active",
                LocalDate.of(2026, 2, 15), LocalDate.of(2026, 8, 15));
        coordinatorDao.insertCoordinator(coordinator);
        
        CoordinatorDto success = coordinatorDao.getCoordinatorById(testUserId);

        assertNotNull(success);
    }

    @Test
    public void testGetAllCoordinatorsSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(testPersonnelNumber, testUserId,
                "Lic. Karla Jimenez Ortega", "Active", LocalDate.of(2026, 1, 10),
                LocalDate.of(2026, 10, 10));
        
        coordinatorDao.insertCoordinator(coordinator);

        List<CoordinatorDto> coordinators = coordinatorDao.getAllCoordinators();

        assertNotNull(coordinators);
    }
}
