package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sistemapracticasprofesional.logic.interfaces.IUser;

public class UserDao implements IUser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    
    @Override
    public boolean isUserRegistred(UserDto user) {
        String query = "SELECT EXISTS ("
                + "SELECT 1 FROM usuario WHERE nombre = ? AND Contraseña = ?"
                + ") AS existe";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("existe") == 1;
                }
            }

            return false;
        } catch (SQLException e) {
            LOGGER.error("Error checking if user is registered: {}", user.getUserName(), e);
            throw new DaoException("Error verifying user", e);
        }
    }

    public boolean isRecipientRegistred(String userName) {

        String query = "SELECT EXISTS ("
                + "SELECT 1 FROM usuario WHERE nombre = ? "
                + ") AS existe";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("existe") == 1;
                }
            }

            return false;

        } catch (SQLException e) {

            LOGGER.error("Error checking if user is registered: {}", userName, e);
            throw new DaoException("Error verifying user", e);

        }
    }

    public boolean isUserActive (int userId){

        String query = "SELECT EXISTS (SELECT 1 FROM usuario_rol WHERE estado = 1 AND Id_usuario = ?) AS existe";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("existe") == 1;
                }
            }

            return false;

        } catch (SQLException e) {

            LOGGER.error("Error checking if user is registered: {}", userId, e);
            throw new DaoException("Error verifying user", e);

        }

    }


    @Override
    public boolean insertUser(UserDto userDto) {
        String query = "INSERT INTO usuario (Id_usuario, nombre, Contraseña, Id_rol) VALUES (?, ?, ?, ?)";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userDto.getIdUser());
            preparedStatement.setString(2, userDto.getUserName());
            preparedStatement.setString(3, userDto.getPassword());
            preparedStatement.setInt(4, userDto.getIdRole());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {

            LOGGER.error("Error registering user with id {}", userDto.getIdUser(), e);
            throw new DaoException("Error registering user", e);
        }

    }

    public boolean insertUser(Connection connection, UserDto userDto) {
        String query = "INSERT INTO usuario (Id_usuario, nombre, Contraseña, Id_rol) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userDto.getIdUser());
            preparedStatement.setString(2, userDto.getUserName());
            preparedStatement.setString(3, userDto.getPassword());
            preparedStatement.setInt(4, userDto.getIdRole());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error registering user with id {}", userDto.getIdUser(), e);
            throw new DaoException("Error registering user", e);
        }
    }

    @Override
    public boolean updateUser( UserDto userDto ) {
        String query = "UPDATE usuario SET nombre = ?, Contraseña = ? WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userDto.getUserName());
            preparedStatement.setString(2, userDto.getPassword());
            preparedStatement.setInt(3, userDto.getIdUser());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error updating name for user id {}", userDto.getIdUser(), e);
            throw new DaoException("Error updating user name", e);
        }
    }

    @Override
    public boolean deactivateUser(int idUser){

        String query = "UPDATE usuario_rol SET estado = 0 WHERE id_usuario = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
            preparedStatement.setInt(1, idUser);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e){

            LOGGER.error("Errorr al desactivar el usuario con el id {}", idUser);
            throw new DaoException("No se pudo desactivar el usuario", e);

        }
    }

    @Override
    public UserDto getUser(int idUser) {
        String query = "SELECT * FROM usuario WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUser);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    UserDto user = new UserDto();
                    user.setUserName(resultSet.getString("nombre"));
                    user.setPassword(resultSet.getString("Contraseña"));
                    return user;
                }
            }

            return null;
        } catch (SQLException e) {
            LOGGER.error("Error getting user with id {}", idUser, e);
            throw new DaoException("Error getting user", e);
        }
    }

    @Override
    public int getIdUser(UserDto userDto){
        
        int result = 0;
        
        String query = "SELECT Id_usuario FROM usuario WHERE nombre = ? AND contraseña = ?;";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userDto.getUserName());
            preparedStatement.setString(2, userDto.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                   
                    result = resultSet.getInt("Id_usuario");
                    
                }

            }

            return result;

        } catch (SQLException e) {

            LOGGER.error("Error getting id {}", userDto.getUserName(), e);
            throw new DaoException("Error getting user", e);

        }

    }

    public int getIdUserByUserName(String userName){
        
        int result = 0;
        
        String query = "SELECT Id_usuario FROM usuario WHERE nombre = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                   
                    result = resultSet.getInt("Id_usuario");
                    
                }

            }

            return result;

        } catch (SQLException e) {

            LOGGER.error("Error getting id {}", userName, e);
            throw new DaoException("Error getting user", e);

        }

    }

    @Override
    public String getUserType( UserDto userDto ) {
        
        String type = null;

        String query = "SELECT rol.Nombre AS tipo_usuario "
        + "FROM usuario "
        + "INNER JOIN usuario_rol ON usuario.Id_usuario = usuario_rol.Id_usuario "
        + "INNER JOIN rol ON usuario_rol.Id_rol = rol.Id_rol "
        + "WHERE usuario.Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                
            preparedStatement.setInt(1, userDto.getIdUser());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    type = resultSet.getString("tipo_usuario");
                }

            }

        } catch (SQLException e) {
            LOGGER.error("Error checking if user is registered: {}", userDto.getUserName(), e);
            throw new DaoException("Error verifying user", e);
        }

        return type;
        
    }

    public boolean existsUserName(String userName){
        String query = "SELECT EXISTS (SELECT 1 FROM usuario WHERE nombre = ?) AS existe";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                
            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("existe") == 1;
                }

            }

            return false;

        } catch (SQLException e) {
            LOGGER.error("Error checking if user is registered: {}", userName, e);
            throw new DaoException("Error verifying user", e);
        }
        
    }

    public boolean existsUserId(int idUser) {
        String query = "SELECT EXISTS (SELECT 1 FROM usuario WHERE Id_usuario = ?) AS existe";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUser);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt("existe") == 1;
            }
        } catch (SQLException e) {
            LOGGER.error("Error checking if user id exists: {}", idUser, e);
            throw new DaoException("Error verifying user id", e);
        }
    }

    
}
