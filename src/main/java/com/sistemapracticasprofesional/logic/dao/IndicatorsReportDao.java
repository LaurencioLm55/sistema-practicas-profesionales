package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.IndicatorsReportDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndicatorsReportDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorsReportDao.class);

    public IndicatorsReportDto getIndicators() throws DaoException {

        IndicatorsReportDto indicators = new IndicatorsReportDto();

        String query = "SELECT "
                + "(SELECT COUNT(*) FROM practicante WHERE EstadoPracticante = 1) AS totalActiveInterns, "
                + "(SELECT COUNT(*) FROM practicante WHERE EstadoPracticante = 0) AS totalInactiveInterns, "
                + "(SELECT COUNT(*) FROM practicante WHERE IdProyecto IS NOT NULL) AS internsWithProject, "
                + "(SELECT COUNT(*) FROM practicante WHERE EstadoPracticante = 1 AND IdProyecto IS NULL) AS internsWithoutProject, "
                + "(SELECT COUNT(*) FROM proyecto) AS totalProjects, "
                + "(SELECT COUNT(*) FROM organizacion_vinculada) AS totalAffiliatedOrganizations, "
                + "(SELECT AVG(Calificacion) FROM reporteavances) AS averageMonthlyScore";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {

                indicators.setTotalActiveInterns(resultSet.getInt("totalActiveInterns"));
                indicators.setTotalInactiveInterns(resultSet.getInt("totalInactiveInterns"));
                indicators.setInternsWithProject(resultSet.getInt("internsWithProject"));
                indicators.setInternsWithoutProject(resultSet.getInt("internsWithoutProject"));
                indicators.setTotalProjects(resultSet.getInt("totalProjects"));
                indicators.setTotalAffiliatedOrganizations(resultSet.getInt("totalAffiliatedOrganizations"));
                indicators.setAverageMonthlyScore(resultSet.getFloat("averageMonthlyScore"));

            }

        } catch (SQLException e) {

            LOGGER.error("Error obteniendo indicadores", e);
            throw new DaoException("Error al obtener los indicadores del reporte", e);

        }

        return indicators;

    }

}
