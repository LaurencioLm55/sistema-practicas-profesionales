package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dao.CoordinatorDao;
import com.sistemapracticasprofesional.logic.dao.RoleDao;
import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dao.UserRoleDao;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.ControllerException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.util.PasswordUtils;
import com.sistemapracticasprofesional.logic.validators.CoordinatorValidator;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class CoordinatorController {

    private static final int MIN_USER_ID = 100000;
    private static final int USER_ID_RANGE = 900000;

    private static final String COORDINATOR_ROLE_NAME = "Coordinador";

    private final CoordinatorDao coordinatorDao = new CoordinatorDao();
    private final UserDao userDao = new UserDao();
    private final UserRoleDao userRoleDao = new UserRoleDao();
    private final RoleDao roleDao = new RoleDao();
    private final CoordinatorValidator validator = new CoordinatorValidator();

    public void registerCoordinator(UserDto userDto, CoordinatorDto coordinatorDto)
            throws ValidationException, BusinessLogicException, ControllerException {

        validator.validateCoordinatorRegistration(coordinatorDto, userDto.getPassword());

        if (coordinatorDao.existsActiveCoordinator()) {
            throw new BusinessLogicException("Ya existe un coordinador activo. Solo puede haber uno a la vez.");
        }

        if (coordinatorDao.existsByPersonalNumber(coordinatorDto.getPersonnelNumber())) {
            throw new BusinessLogicException("El número de personal ya está registrado.");
        }

        userDto.setIdUser(generateUserId());
        userDto.setIdRole(resolveCoordinatorRoleId());
        userDto.setPassword(PasswordUtils.hashPassword(userDto.getPassword()));
        coordinatorDto.setState("Activo");
        coordinatorDto.setEntryDate(LocalDate.now());
        coordinatorDto.setUserId(userDto.getIdUser());

        executeRegistration(userDto, coordinatorDto);
    }

    private void executeRegistration(UserDto userDto, CoordinatorDto coordinatorDto)
            throws ControllerException {

        Connection connection = DatabaseConnection.getConnection();
        boolean previousAutoCommit = true;

        try {
            previousAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            userDao.insertUser(connection, userDto);
            coordinatorDao.insertCoordinator(connection, coordinatorDto);
            userRoleDao.insertUserRole(connection, userDto.getIdUser(), userDto.getIdRole());

            connection.commit();
        } catch (SQLException | DaoException e) {
            rollback(connection);
            throw new ControllerException("No se pudo registrar el coordinador", e);
        } finally {
            restoreAutoCommit(connection, previousAutoCommit);
        }
    }

    private int resolveCoordinatorRoleId() throws ControllerException {
        try {
            return roleDao.getRoleIdByName(COORDINATOR_ROLE_NAME);
        } catch (DaoException e) {
            throw new ControllerException("No se pudo obtener el rol de Coordinador", e);
        }
    }

    private int generateUserId() {
        int idUser;
        do {
            idUser = MIN_USER_ID + (int) (Math.random() * USER_ID_RANGE);
        } while (userDao.existsUserId(idUser));
        return idUser;
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Error al hacer rollback del registro", e);
        }
    }

    private void restoreAutoCommit(Connection connection, boolean previousAutoCommit) {
        try {
            connection.setAutoCommit(previousAutoCommit);
        } catch (SQLException e) {
            throw new DaoException("Error al restaurar estado de la conexión", e);
        }
    }
}
