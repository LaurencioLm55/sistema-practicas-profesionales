package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.MessageDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.interfaces.IMessage;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDao implements IMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDao.class);

    @Override
    public boolean insertMessage(MessageDto message) {
        String query = "INSERT INTO mensaje (Remitente, Destinatario, Asunto, "
                + "Contenido_de_mensaje, Nombre_remitente, Nombre_Destinatario, Fecha)" 
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, message.getSenderUserId());
            preparedStatement.setInt(2, message.getReceiverUserId());
            preparedStatement.setString(3, message.getSubject());
            preparedStatement.setString(4, message.getContent());
            preparedStatement.setString(5, message.getSenderUserName());
            preparedStatement.setString(6, message.getSenderUserName());
            preparedStatement.setDate(7, Date.valueOf(message.getMessageDate()));

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error sending message from user {} to user {}",
                    message.getSenderUserId(), message.getReceiverUserId(), e);
            throw new DaoException("Error sending message", e);
        }
    }

    @Override
    public MessageDto getMessageById(int messageId) {
        String query = "SELECT * FROM mensaje WHERE Id_mensaje = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, messageId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToDto(resultSet);
                    
                }

            }

            return null;

        } catch (SQLException e) {
            LOGGER.error("Error getting message with id {}", messageId, e);
            throw new DaoException("Error getting message", e);
        }
    }

    @Override
    public List<MessageDto> getInboxByUserId(int receiverUserId) {
        
        List<MessageDto> messageList = new ArrayList<>();
        String query = "SELECT Id_mensaje, Nombre_remitente, asunto, Contenido_de_mensaje FROM mensaje WHERE Destinatario = ? ORDER BY "
                + "Id_mensaje DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, receiverUserId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    
                    MessageDto messageDto = new MessageDto();

                    messageDto.setMessageId(resultSet.getInt("Id_mensaje"));
                    messageDto.setSenderUserName(resultSet.getString("Nombre_remitente"));
                    messageDto.setSubject(resultSet.getString("asunto"));
                    messageDto.setContent(resultSet.getString("Contenido_de_mensaje"));

                    messageList.add(messageDto);

                }
            }

            return messageList;

        } catch (SQLException e) {
            LOGGER.error("Error getting inbox for user {}", receiverUserId, e);
            throw new DaoException("Error getting inbox", e);
        }
    }

    @Override
    public List<MessageDto> getSentMessagesByUserId(int senderUserId) {
        List<MessageDto> messageList = new ArrayList<>();
        String query = "SELECT * FROM mensaje WHERE Remitente = ? ORDER BY "
                + "Id_mensaje DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, senderUserId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    messageList.add(mapResultSetToDto(resultSet));
                }
            }

            return messageList;

        } catch (SQLException e) {
            LOGGER.error("Error getting sent messages for user {}", senderUserId, e);
            throw new DaoException("Error getting sent messages", e);
        }
    }

    @Override
    public boolean deleteMessage(int messageId) {
        String query = "DELETE FROM mensaje WHERE Id_mensaje = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, messageId);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error deleting message {}", messageId, e);
            throw new DaoException("Error deleting message", e);
        }
    }

    private MessageDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        return new MessageDto(
                resultSet.getInt("Id_mensaje"),
                resultSet.getInt("Remitente"),
                resultSet.getInt("Destinatario"),
                resultSet.getString("Asunto"),
                resultSet.getString("Contenido_de_mensaje"),
                resultSet.getString("Nombre_remitente"),
                resultSet.getString("Nombre_Destinatario"),
                resultSet.getDate("fecha").toLocalDate()
        );
    }
}
