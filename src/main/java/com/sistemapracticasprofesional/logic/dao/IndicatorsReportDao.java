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

    public IndicatorsReportDto getIndicators() {
        IndicatorsReportDto indicators = new IndicatorsReportDto();

        try (Connection connection = DatabaseConnection.getConnection()) {
            indicators.setTotalActiveInterns(queryCount(connection,
                    "SELECT COUNT(*) FROM practicante WHERE EstadoPracticante = 1"));

            indicators.setTotalInactiveInterns(queryCount(connection,
                    "SELECT COUNT(*) FROM practicante WHERE EstadoPracticante = 0"));

            indicators.setInternsWithProject(queryCount(connection,
                    "SELECT COUNT(*) FROM practicante WHERE IdProyecto IS NOT NULL"));

            indicators.setInternsWithoutProject(queryCount(connection,
                    "SELECT COUNT(*) FROM practicante WHERE EstadoPracticante = 1 AND IdProyecto IS NULL"));

            indicators.setTotalProjects(queryCount(connection,
                    "SELECT COUNT(*) FROM proyecto"));

            indicators.setTotalAffiliatedOrganizations(queryCount(connection,
                    "SELECT COUNT(*) FROM organizacion_vinculada"));

            indicators.setAverageMonthlyScore(queryAverage(connection,
                    "SELECT AVG(Calificacion) FROM reporteavances"));

        } catch (SQLException e) {
            LOGGER.error("Error obteniendo indicadores", e);
            throw new DaoException("Error al obtener los indicadores del reporte", e);
        }

        return indicators;
    }

    private int queryCount(Connection connection, String sql) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private float queryAverage(Connection connection, String sql) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getFloat(1);
            }
        }
        return 0f;
    }
}
