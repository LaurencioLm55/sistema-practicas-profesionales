package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sistemapracticasprofesional.logic.interfaces.IIntern;

public class InternDao implements IIntern {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternDao.class);

    @Override
    public boolean insertIntern(InternDto intern) {
        String query = "INSERT INTO practicante "
                + "(Matricula, Id_usuario, Nombre, Edad, Genero, Carrera, LenguaIndigena) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, intern.getStudentId());
            if (intern.getUserId() != null) {
                preparedStatement.setInt(2, intern.getUserId());
            } else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }
            preparedStatement.setString(3, intern.getName());
            preparedStatement.setInt(4, intern.getAge());
            preparedStatement.setString(5, intern.getGender());
            preparedStatement.setString(6, intern.getMajor());
            preparedStatement.setString(7, intern.getIndigenousLanguage());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error inserting intern with student id {}", intern.getStudentId(), e);
            throw new DaoException("Error registering intern", e);
        }
    }

    public boolean insertIntern(Connection connection, InternDto intern) {
        String query = "INSERT INTO practicante "
                + "(Matricula, Id_usuario, Nombre, Edad, Genero, Carrera, LenguaIndigena) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, intern.getStudentId());
            if (intern.getUserId() != null) {
                preparedStatement.setInt(2, intern.getUserId());
            } else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }
            preparedStatement.setString(3, intern.getName());
            preparedStatement.setInt(4, intern.getAge());
            preparedStatement.setString(5, intern.getGender());
            preparedStatement.setString(6, intern.getMajor());
            preparedStatement.setString(7, intern.getIndigenousLanguage());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error inserting intern with student id {}", intern.getStudentId(), e);
            throw new DaoException("Error registering intern", e);
        }
    }

    @Override
    public boolean updateIntern(InternDto intern) {
        String query = "UPDATE practicante SET Nombre = ?, Edad = ?, Genero = ?, Carrera = ?, LenguaIndigena = ? "
                + "WHERE Matricula = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, intern.getName());
            preparedStatement.setInt(2, intern.getAge());
            preparedStatement.setString(3, intern.getGender());
            preparedStatement.setString(4, intern.getMajor());
            preparedStatement.setString(5, intern.getIndigenousLanguage());
            preparedStatement.setString(6, intern.getStudentId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error updating intern with student id {}", intern.getStudentId(), e);
            throw new DaoException("Error updating intern", e);
        }
    }

    @Override
    public boolean deleteIntern(String studentId) {
        String query = "DELETE FROM practicante WHERE Matricula = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error deleting intern with student id {}", studentId, e);
            throw new DaoException("Error deleting intern", e);
        }
    }

    @Override
    public InternDto getInternByStudentId(String studentId) {
        String query = "SELECT * FROM practicante WHERE Matricula = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDto(resultSet);
                }
            }

            return null;

        } catch (SQLException e) {
            LOGGER.error("Error getting intern with student id {}", studentId, e);
            throw new DaoException("Error getting intern", e);
        }
    }

    @Override
    public List<InternDto> getAllInterns() {
        List<InternDto> internList = new ArrayList<>();
        String query = "SELECT * FROM practicante";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                internList.add(mapResultSetToDto(resultSet));
            }

            return internList;

        } catch (SQLException e) {
            LOGGER.error("Error getting intern list", e);
            throw new DaoException("Error getting intern list", e);
        }
    }

    @Override
    public boolean assignProject(String studentId, int projectId) {

        String query = "UPDATE practicante SET IdProyecto = ? WHERE Matricula = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
                preparedStatement.setInt(1, projectId);
                preparedStatement.setString(2, studentId);

                return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e){

            LOGGER.error("Error al asigar proyrcyo", e);
            throw new DaoException("Error al asignar proyecto", e);

        }

    }

    private InternDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        return new InternDto(
                resultSet.getString("Matricula"),
                resultSet.getObject("Id_usuario") != null ? resultSet.getInt("Id_usuario") : null,
                resultSet.getInt("Edad"),
                resultSet.getString("Nombre"),
                resultSet.getString("LenguaIndigena"),
                resultSet.getString("Genero"),
                resultSet.getString("Carrera")
        );
    }
}
