package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dao.CoordinatorDao;
import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dao.ProfessorDao;
import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dao.UserRoleDao;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.util.PasswordUtils;
import com.sistemapracticasprofesional.logic.validators.UserValidator;
import java.sql.Connection;
import java.sql.SQLException;

public class UserRegisterService {

    private static final int MIN_USER_ID = 100000;
    private static final int USER_ID_RANGE = 900000;

    private UserDao userDao = new UserDao();
    private CoordinatorDao coordinatorDao = new CoordinatorDao();
    private ProfessorDao professorDao = new ProfessorDao();
    private InternDao internDao = new InternDao();
    private UserRoleDao userRoleDao = new UserRoleDao();

    public void registerCoordinator(UserDto userDto, String confirmPassword, CoordinatorDto coordinatorDto)
            throws BusinessLogicException {

        validateUser(userDto, confirmPassword);
        validateCoordinator(coordinatorDto);
        prepareUser(userDto);
        coordinatorDto.setUserId(userDto.getIdUser());

        executeRegistration(userDto, () -> coordinatorDao.insertCoordinator(getConnection(), coordinatorDto));
    }

    public void registerProfessor(UserDto userDto, String confirmPassword, ProfessorDto professorDto)
            throws BusinessLogicException {

        validateUser(userDto, confirmPassword);
        validateProfessor(professorDto);
        prepareUser(userDto);
        professorDto.setUserId(userDto.getIdUser());

        executeRegistration(userDto, () -> professorDao.insertProfessor(getConnection(), professorDto));
    }

    public void registerIntern(UserDto userDto, String confirmPassword, InternDto internDto)
            throws BusinessLogicException {

        validateUser(userDto, confirmPassword);
        validateIntern(internDto);
        prepareUser(userDto);
        internDto.setUserId(userDto.getIdUser());

        executeRegistration(userDto, () -> internDao.insertIntern(getConnection(), internDto));
    }

    private void validateUser(UserDto userDto, String confirmPassword) throws BusinessLogicException {
        UserValidator validator = new UserValidator(userDto);

        if (!validator.isUserValid()) {
            throw new BusinessLogicException("Datos del usuario invalidos");
        }

        if (!userDto.getPassword().equals(confirmPassword)) {
            throw new BusinessLogicException("Las contrasenas no coinciden");
        }

        if (userDao.existsUserName(userDto.getUserName())) {
            throw new BusinessLogicException("El nombre del usuario ya existe");
        }
    }

    private void prepareUser(UserDto userDto) {
        userDto.setIdUser(generateUserId());
        userDto.setUserName(userDto.getUserName().trim());
        userDto.setPassword(PasswordUtils.hashPassword(userDto.getPassword()));
    }

    private void executeRegistration(UserDto userDto, SpecializedRegister specializedRegister)
            throws BusinessLogicException {

        Connection connection = DatabaseConnection.getConnection();
        boolean previousAutoCommit = true;

        try {
            previousAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            userDao.insertUser(connection, userDto);
            specializedRegister.insert();
            userRoleDao.insertUserRole(connection, userDto.getIdUser(), userDto.getIdRole());

            connection.commit();
        } catch (SQLException | DaoException e) {
            rollback(connection);
            throw new BusinessLogicException("No se pudo registrar el usuario");
        } finally {
            restoreAutoCommit(connection, previousAutoCommit);
        }
    }

    private int generateUserId() {
        int idUser;

        do {
            idUser = MIN_USER_ID + (int) (Math.random() * USER_ID_RANGE);
        } while (userDao.existsUserId(idUser));

        return idUser;
    }

    private void validateCoordinator(CoordinatorDto coordinatorDto) throws BusinessLogicException {
        if (coordinatorDto == null
                || coordinatorDto.getPersonnelNumber() <= 0
                || isBlank(coordinatorDto.getName())
                || isBlank(coordinatorDto.getState())
                || coordinatorDto.getEntryDate() == null) {
            throw new BusinessLogicException("Datos del coordinador invalidos");
        }

        coordinatorDto.setName(coordinatorDto.getName().trim());
        coordinatorDto.setState(coordinatorDto.getState().trim());
    }

    private void validateProfessor(ProfessorDto professorDto) throws BusinessLogicException {
        if (professorDto == null
                || professorDto.getStaffNumber() <= 0
                || isBlank(professorDto.getName())
                || isBlank(professorDto.getShift())) {
            throw new BusinessLogicException("Datos del profesor invalidos");
        }

        professorDto.setName(professorDto.getName().trim());
        professorDto.setShift(professorDto.getShift().trim());
    }

    private void validateIntern(InternDto internDto) throws BusinessLogicException {
        if (internDto == null
                || isBlank(internDto.getStudentId())
                || internDto.getAge() <= 0
                || isBlank(internDto.getName())
                || isBlank(internDto.getGender())
                || isBlank(internDto.getMajor())) {
            throw new BusinessLogicException("Datos del practicante invalidos");
        }

        internDto.setStudentId(internDto.getStudentId().trim());
        internDto.setName(internDto.getName().trim());
        internDto.setGender(internDto.getGender().trim());
        internDto.setMajor(internDto.getMajor().trim());

        if (isBlank(internDto.getIndigenousLanguage())) {
            internDto.setIndigenousLanguage(null);
        } else {
            internDto.setIndigenousLanguage(internDto.getIndigenousLanguage().trim());
        }
    }

    private Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Error rolling back registration", e);
        }
    }

    private void restoreAutoCommit(Connection connection, boolean previousAutoCommit) {
        try {
            connection.setAutoCommit(previousAutoCommit);
        } catch (SQLException e) {
            throw new DaoException("Error restoring connection state", e);
        }
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }

    @FunctionalInterface
    private interface SpecializedRegister {
        boolean insert();
    }
}
