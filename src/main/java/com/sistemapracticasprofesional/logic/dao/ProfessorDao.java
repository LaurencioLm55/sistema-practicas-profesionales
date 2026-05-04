package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.interfaces.IProfessor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfessorDao implements IProfessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorDao.class);

    @Override
    public boolean insertProfessor(ProfessorDto professor) {
        
        String registerQuery = "INSERT INTO profesor (Numero_de_personal, Id_usuario,"
                + " Nombre, turno) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(registerQuery)) {

            preparedStatement.setInt(1, professor.getStaffNumber());

            if (professor.getUserId() != null) {
                preparedStatement.setInt(2, professor.getUserId());
            } else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }

            preparedStatement.setString(3, professor.getName());
            preparedStatement.setString(4, professor.getShift());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            
            LOGGER.error("Error inserting professor with staff number {}", professor.getStaffNumber(), e);
            throw new DaoException("Error registering professor", e);
        }
    }

    public boolean insertProfessor(Connection connection, ProfessorDto professor) {
        String registerQuery = "INSERT INTO profesor (Numero_de_personal, Id_usuario,"
                + " Nombre, turno) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(registerQuery)) {

            preparedStatement.setInt(1, professor.getStaffNumber());

            if (professor.getUserId() != null) {
                preparedStatement.setInt(2, professor.getUserId());
            } else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }

            preparedStatement.setString(3, professor.getName());
            preparedStatement.setString(4, professor.getShift());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error inserting professor with staff number {}", professor.getStaffNumber(), e);
            throw new DaoException("Error registering professor", e);
        }
    }

    @Override
    public boolean updateProfessor(ProfessorDto professor) {
        
        String updateQuery = "UPDATE profesor SET Id_usuario = ?, Nombre = ?, turno = ?"
            + " WHERE Numero_de_personal = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            if (professor.getUserId() != null) {
                preparedStatement.setInt(1, professor.getUserId());
            } else {
                preparedStatement.setNull(1, java.sql.Types.INTEGER);
            }

            preparedStatement.setString(2, professor.getName());
            preparedStatement.setString(3, professor.getShift());
            preparedStatement.setInt(4, professor.getStaffNumber());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            
            LOGGER.error("Error updating professor with staff number {}", professor.getStaffNumber(), e);
            throw new DaoException("Error updating professor", e);
        }
    }

    @Override
    public boolean deleteProfessor(int staffNumber) {
        
        String deleteQuery = "DELETE FROM profesor WHERE Numero_de_personal = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, staffNumber);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            
            LOGGER.error("Error deleting professor with staff number {}", staffNumber, e);
            throw new DaoException("Error deleting professor", e);
        }
    }

    @Override
    public ProfessorDto getProfessorByStaffNumber(int staffNumber) {
        
        String getByIdQuery = "SELECT * FROM profesor WHERE Numero_de_personal = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getByIdQuery)) {

            preparedStatement.setInt(1, staffNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDto(resultSet);
                }
            }

            return null;

        } catch (SQLException e) {
            
            LOGGER.error("Error getting professor with staff number {}", staffNumber, e);
            throw new DaoException("Error getting professor", e);
        }
    }

    @Override
    public List<ProfessorDto> getAllProfessors() {
        
        List<ProfessorDto> professorList = new ArrayList<>();

        String getAllQuery = "SELECT * FROM profesor";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                professorList.add(mapResultSetToDto(resultSet));
            }

            return professorList;

        } catch (SQLException e) {
            
            LOGGER.error("Error getting professor list", e);
            throw new DaoException("Error getting professor list", e);
        }
    }

    private ProfessorDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        Integer userId = resultSet.getObject("Id_usuario") != null
                ? resultSet.getInt("Id_usuario")
                : null;

        return new ProfessorDto(
                resultSet.getInt("Numero_de_personal"),
                userId,
                resultSet.getString("Nombre"),
                resultSet.getString("turno")
        );
    }
}
