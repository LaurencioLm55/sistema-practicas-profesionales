package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.CoordinatorDao;
import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dao.ProfessorDao;
import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.validators.UserValidator;

public class UserRegisterService {

    private static final int MIN_USER_ID = 100000;
    private static final int USER_ID_RANGE = 900000;

    private UserDao userDao = new UserDao();
    private CoordinatorDao coordinatorDao = new CoordinatorDao();
    private ProfessorDao professorDao = new ProfessorDao();
    private InternDao internDao = new InternDao();

    public void registerCoordinator(UserDto userDto, CoordinatorDto coordinatorDto)
            throws BusinessLogicException {

        validateUser(userDto);
        prepareUser(userDto);

        userDao.insertUser(userDto);
        coordinatorDto.setUserId(userDto.getIdUser());
        coordinatorDao.insertCoordinator(coordinatorDto);
    }

    public void registerProfessor(UserDto userDto, ProfessorDto professorDto)
            throws BusinessLogicException {

        validateUser(userDto);
        prepareUser(userDto);

        userDao.insertUser(userDto);
        professorDto.setUserId(userDto.getIdUser());
        professorDao.insertProfessor(professorDto);
    }

    public void registerIntern(UserDto userDto, InternDto internDto)
            throws BusinessLogicException {

        validateUser(userDto);
        prepareUser(userDto);

        userDao.insertUser(userDto);
        internDto.setUserId(userDto.getIdUser());
        internDao.insertIntern(internDto);
    }

    private void validateUser(UserDto userDto) throws BusinessLogicException {
        UserValidator validator = new UserValidator(userDto);

        if (!validator.isUserValid()) {
            throw new BusinessLogicException("Datos del usuario invalidos");
        }

        if (userDao.existsUserName(userDto.getUserName())) {
            throw new BusinessLogicException("El nombre del usuario ya existe");
        }
    }

    private void prepareUser(UserDto userDto) {
        userDto.setIdUser(generateUserId());
    }

    private int generateUserId() {
        int idUser;

        do {
            idUser = MIN_USER_ID + (int) (Math.random() * USER_ID_RANGE);
        } while (userDao.existsUserId(idUser));

        return idUser;
    }
}
