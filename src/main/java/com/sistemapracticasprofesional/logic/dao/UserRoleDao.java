package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRoleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleDao.class);

    public boolean insertUserRole(Connection connection, int idUser, int idRole) {
        String query = "INSERT INTO usuario_rol (Id_usuario, Id_rol, estado) VALUES (?, ?, 1)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idRole);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error assigning role {} to user {}", idRole, idUser, e);
            throw new DaoException("Error assigning user role", e);
        }
    }
}
