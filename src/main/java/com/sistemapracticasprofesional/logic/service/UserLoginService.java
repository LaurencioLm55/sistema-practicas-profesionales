package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.validators.UserValidator;
import com.sistemapracticasprofesional.presentation.util.UserSession;


public class UserLoginService {

    private UserDao userDao = new UserDao();
    private UserValidator validator;

    public String logginUser(UserDto userDto) throws BusinessLogicException{
        
        String type = null;

        validator = new UserValidator(userDto);
        

        if(validator.isUserValid() == true){
           
            if(userDao.isUserRegistred(userDto) == true){
                
                userDto.setIdUser(userDao.getIdUser(userDto));

                type = userDao.getUserType(userDto);

                UserSession.getInstance().initializeSession(userDto, type);

            }else{

                throw new BusinessLogicException("El usuario o contreaseña no son validos");

            }

        }else{

            throw new BusinessLogicException("El usuario o contreaseña no son validos");

        }

        return type;

<<<<<<< HEAD
    }

    private void startPresentationWhitType(String typeUser){


        switch (typeUser) {
            case "Practicante":

                startIntern();

                break;

            case "Profesor":

                startProfessor();

                break;
            
            case "Coordinador":

                startCoordinator();

                break;
        
            default:

                break;
        }

    }

    private void startIntern(){

        resourcePath = "src/main/java/com/sistemapracticasprofesional/presentation/views/GuiCoordinatorMenu.fxml";
        namePath = "/com/sistemapracticasprofesional/presentation/views/GuiCoordinatorMenu.fxml";
        title = "Ventana princiapal Practicante";


        navigationServices = new NavigationServices(resourcePath, namePath, title);

    }

    private void startProfessor(){

        resourcePath = "src/main/java/com/sistemapracticasprofesional/presentation/views/";
        namePath = "/com/sistemapracticasprofesional/presentation/views/";
        title = "Ventana princiapal Profesor";

        navigationServices = new NavigationServices(resourcePath, namePath, title);

    }

    private void startCoordinator(){

        resourcePath = "src/main/java/com/sistemapracticasprofesional/presentation/views/";
        namePath = "/com/sistemapracticasprofesional/presentation/views/";
        title = "Ventana princiapal Coordinador";

        navigationServices = new NavigationServices(resourcePath, namePath, title);
=======
>>>>>>> 7a9df57abf7701f57db5978ea4974e7f5155b7a9

    }


}

