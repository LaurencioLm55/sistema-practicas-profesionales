package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.interfaces.IProject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectDao implements IProject {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDao.class);

    @Override
    public boolean insertProject(ProjectDto project) throws DaoException {

        boolean operationSuccess = false;
        
        String registerQuery = "INSERT INTO proyecto (IdProyecto, Id_organizacion, "
            + "Nombre, Descripcion_general, Metodologia, Recursos, Objetivos_medios, "
            + "Objetivo_general, Objetivos_inmediatos, Responsabilidades, "
            + "Nombre_Encargado, e_mail_Encargado, cargo_Encargado) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(registerQuery)) {

            preparedStatement.setInt(1, project.getProjectId());
            preparedStatement.setInt(2, project.getLinkedOrganizationId());
            preparedStatement.setString(3, project.getProjectName());
            preparedStatement.setString(4, project.getProjectDescription());
            preparedStatement.setString(5, project.getProjectMethodology());
            preparedStatement.setString(6, project.getProjectResources());
            preparedStatement.setString(7, project.getMidtermProjectObjectives());
            preparedStatement.setString(8, project.getGeneralProjectObjectives());
            preparedStatement.setString(9, project.getInmediateProjectObjectives());
            preparedStatement.setString(10, project.getProjectResponsabilities());
            preparedStatement.setString(11, project.getProjectAttendantName());
            preparedStatement.setString(12, project.getProjectAttendantEmail());
            preparedStatement.setString(13, project.getProjectAttendantPosition());
            preparedStatement.executeUpdate();

            operationSuccess = true;

        } catch (SQLException e) {

            LOGGER.error("Error registering project", e);
            throw new DaoException("Error registering project", e);
        }
        return operationSuccess;
    }

    @Override
    public boolean updateProject(ProjectDto project) throws DaoException {

        boolean operationSuccess = false;

        String updateQuery = "UPDATE proyecto SET Id_organizacion = ?, "
            + "Nombre = ?, Descripcion_general = ?, Metodologia = ?, Recursos = ?, "
            + "Objetivos_medios = ?, Objetivo_general = ?, Objetivos_inmediatos = ?, "
            + "Responsabilidades = ?, Nombre_Encargado = ?, e_mail_Encargado = ?, "
            + "cargo_Encargado = ? WHERE IdProyecto = ?";

        try (Connection connection = DatabaseConnection.getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setInt(1, project.getLinkedOrganizationId());
            preparedStatement.setString(2, project.getProjectName());
            preparedStatement.setString(3, project.getProjectDescription());
            preparedStatement.setString(4, project.getProjectMethodology());
            preparedStatement.setString(5, project.getProjectResources());
            preparedStatement.setString(6, project.getMidtermProjectObjectives());
            preparedStatement.setString(7, project.getGeneralProjectObjectives());
            preparedStatement.setString(8, project.getInmediateProjectObjectives());
            preparedStatement.setString(9, project.getProjectResponsabilities());
            preparedStatement.setString(10, project.getProjectAttendantName());
            preparedStatement.setString(11, project.getProjectAttendantEmail());
            preparedStatement.setString(12, project.getProjectAttendantPosition());
            preparedStatement.setInt(13, project.getProjectId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                operationSuccess = true;
            }

        } catch (SQLException e) {

            LOGGER.error("Error updating project", e);
            throw new DaoException("Error updating project", e);
        }
        return operationSuccess;
    }

    @Override
    public boolean deleteProject(int projectId) throws DaoException {

        boolean operationSuccess = false;

        String deleteQuery = "DELETE FROM proyecto WHERE IdProyecto = ?";

        try (Connection connection = DatabaseConnection.getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, projectId);
            preparedStatement.executeUpdate();

            operationSuccess = true;

        } catch (SQLException e) {

            LOGGER.error("Error deleting project by id: {}", projectId, e);
            throw new DaoException("Error deleting project with id: "
                    + projectId, e);
        }
        return operationSuccess;
    }

    @Override
    public ProjectDto getProjectById(int projectId) throws DaoException {

        ProjectDto project = null;

        String getByIdQuery = "SELECT * FROM proyecto WHERE IdProyecto = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getByIdQuery)) {

            preparedStatement.setInt(1, projectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    project = mapResultSetToDto(resultSet);
                }
            }
        } catch (SQLException e) {

            LOGGER.error("Error fetching project by id: {}", projectId, e);
            throw new DaoException("Error getting project with id: "
                    + projectId, e);
        }
        return project;
    }

    @Override
    public List<ProjectDto> getAllProjects() throws DaoException {

        List<ProjectDto> projects = new ArrayList<>();

        String getAllQuery = "SELECT * FROM proyecto";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllQuery);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                projects.add(mapResultSetToDto(resultSet));

            }

        } catch (SQLException e) {

            LOGGER.error("Error fetching all the projects", e);
            throw new DaoException("Error getting project list", e);
        }
        return projects;
    }

    @Override
    public boolean isProjectRegistrer (ProjectDto projectDto){

        String query = "SELECT EXISTS ("
                + "SELECT 1 FROM usuario WHERE nombre = ? AND Contraseña = ?"
                + ") AS existe";

         try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, projectDto.getProjectName());
            preparedStatement.setString(2, projectDto.getProjectDescription());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("existe") == 1;
                }
            }

            return false;

        } catch (SQLException e) {
            LOGGER.error("Error checking if user is registered: {}", projectDto.getProjectAttendantName(), e);
            throw new DaoException("Error verifying user", e);
        }
    }

    private ProjectDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        ProjectDto project = new ProjectDto();
        project.setProjectId(resultSet.getInt("IdProyecto"));
        project.setLinkedOrganizationId(resultSet.getInt("Id_organizacion"));
        project.setProjectName(resultSet.getString("Nombre"));
        project.setProjectDescription(resultSet.getString("Descripcion_general"));
        project.setProjectMethodology(resultSet.getString("Metodologia"));
        project.setProjectResources(resultSet.getString("Recursos"));
        project.setMidtermProjectObjectives(resultSet.getString("Objetivos_medios"));
        project.setGeneralProjectObjectives(resultSet.getString("Objetivo_general"));
        project.setInmediateProjectObjectives(resultSet.getString("Objetivos_inmediatos"));
        project.setProjectResponsabilities(resultSet.getString("Responsabilidades"));
        project.setProjectAttendantName(resultSet.getString("Nombre_Encargado"));
        project.setProjectAttendantEmail(resultSet.getString("e_mail_Encargado"));
        project.setProjectAttendantPosition(resultSet.getString("cargo_Encargado"));

        return project;
    }

}
