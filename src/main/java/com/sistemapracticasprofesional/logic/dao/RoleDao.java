package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.RoleDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDao.class);

    public List<RoleDto> getAllRoles() {
        List<RoleDto> roles = new ArrayList<>();
        String query = "SELECT Id_rol, Nombre FROM rol ORDER BY Id_rol";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                RoleDto roleDto = new RoleDto();
                roleDto.setIdRole(resultSet.getInt("Id_rol"));
                roleDto.setName(resultSet.getString("Nombre"));
                roles.add(roleDto);
            }

            return roles;
        } catch (SQLException e) {
            LOGGER.error("Error getting roles", e);
            throw new DaoException("Error getting roles", e);
        }
    }
}
