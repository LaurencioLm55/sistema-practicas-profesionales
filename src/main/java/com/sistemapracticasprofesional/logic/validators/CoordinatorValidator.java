package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public class CoordinatorValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{10,}$";
    private static final int MAX_NAME_LENGTH = 100;

    public void validateCoordinatorRegistration(CoordinatorDto coordinatorDto, String password)
            throws ValidationException {
        validateName(coordinatorDto.getName());
        validatePersonnelNumber(coordinatorDto.getPersonnelNumber());
        validatePassword(password);
    }

    private void validateName(String name) throws ValidationException {
        if (name == null || name.isBlank()) {
            throw new ValidationException("El nombre del coordinador no puede estar vacío");
        }
        if (name.trim().length() > MAX_NAME_LENGTH) {
            throw new ValidationException("El nombre del coordinador no puede exceder 100 caracteres");
        }
    }

    private void validatePersonnelNumber(int personnelNumber) throws ValidationException {
        if (personnelNumber <= 0) {
            throw new ValidationException("El número de personal debe ser un valor numérico positivo");
        }
    }

    private void validatePassword(String password) throws ValidationException {
        if (password == null || password.isBlank()) {
            throw new ValidationException("La contraseña no puede estar vacía");
        }
        if (!password.matches(PASSWORD_PATTERN)) {
            throw new ValidationException(
                    "La contraseña debe tener mínimo 10 caracteres, una mayúscula, una minúscula y un número");
        }
    }
}
