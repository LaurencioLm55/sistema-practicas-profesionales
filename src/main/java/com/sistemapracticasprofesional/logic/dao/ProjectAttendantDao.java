package com.sistemapracticasprofesional.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.ProjectAttendantDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.interfaces.IProjectAttendant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectAttendantDao implements IProjectAttendant{
    
    @Override
    public boolean insertProjectAttendant(ProjectAttendantDto projectAttendantDto){

        boolean result = false; 

        String query = "INSERT INTO encargado_de_proyecto (Id_encargado_de_proyecto, nombre_de_encargado, cargo_encargado, email_encargado)" 
        + "VALUES (?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

                preparedStatement.setInt(1, projectAttendantDto.getIdProjectAttendant());
                preparedStatement.setString(2, projectAttendantDto.getProjectAttendantName());
                preparedStatement.setString(2, projectAttendantDto.getProjectAttendantPosition());
                preparedStatement.setString(3, projectAttendantDto.getProjectAttendantEmail());

                if (preparedStatement.executeUpdate() > 0){
                    result = true;
                }

        }catch (SQLException e){

            throw new DaoException("", e);

        }

        return result;
        
    }
}
