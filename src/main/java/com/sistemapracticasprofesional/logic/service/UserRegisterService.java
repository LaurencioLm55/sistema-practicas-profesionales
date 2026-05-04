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
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.util.PasswordUtils;
import com.sistemapracticasprofesional.logic.validators.RegistrationValidator;
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

        validateRegistrationData(userDto, confirmPassword);
        validateCoordinatorData(coordinatorDto);
        prepareUser(userDto);
        coordinatorDto.setUserId(userDto.getIdUser());

        executeRegistration(userDto, () -> coordinatorDao.insertCoordinator(getConnection(), coordinatorDto));
    }

    public void registerProfessor(UserDto userDto, String confirmPassword, ProfessorDto professorDto)
            throws BusinessLogicException {

        validateRegistrationData(userDto, confirmPassword);
        validateProfessorData(professorDto);
        prepareUser(userDto);
        professorDto.setUserId(userDto.getIdUser());

        executeRegistration(userDto, () -> professorDao.insertProfessor(getConnection(), professorDto));
    }

    public void registerIntern(UserDto userDto, String confirmPassword, InternDto internDto)
            throws BusinessLogicException {

        validateRegistrationData(userDto, confirmPassword);
        validateInternData(internDto);
        prepareUser(userDto);
        internDto.setUserId(userDto.getIdUser());

        executeRegistration(userDto, () -> internDao.insertIntern(getConnection(), internDto));
    }

    private void validateRegistrationData(UserDto userDto, String confirmPassword)
            throws BusinessLogicException {
        validate(() -> RegistrationValidator.validateUser(userDto, confirmPassword));

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

    private void validateCoordinatorData(CoordinatorDto coordinatorDto) throws BusinessLogicException {
        validate(() -> RegistrationValidator.validateCoordinator(coordinatorDto));
    }

    private void validateProfessorData(ProfessorDto professorDto) throws BusinessLogicException {
        validate(() -> RegistrationValidator.validateProfessor(professorDto));
    }

    private void validateInternData(InternDto internDto) throws BusinessLogicException {
        validate(() -> RegistrationValidator.validateIntern(internDto));
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

    private void validate(Validation validation) throws BusinessLogicException {
        try {
            validation.validate();
        } catch (ValidationException e) {
            throw new BusinessLogicException(e.getMessage());
        }
    }

    @FunctionalInterface
    private interface SpecializedRegister {
        boolean insert();
    }

    @FunctionalInterface
    private interface Validation {
        void validate() throws ValidationException;
    }
}
