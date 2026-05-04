package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.sql.Connection;


public interface IUser {

    boolean isUserRegistred(UserDto userDTO) throws DaoException;
    boolean insertUser(UserDto userDto) throws DaoException;
    boolean insertUser(Connection connection, UserDto userDto) throws DaoException;
    boolean updateUser(UserDto userDto) throws DaoException;
    UserDto getUser(int idUser) throws DaoException;
    int getIdUser( UserDto userDto ) throws DaoException;
    String getUserType(UserDto userDto) throws DaoException;
}
