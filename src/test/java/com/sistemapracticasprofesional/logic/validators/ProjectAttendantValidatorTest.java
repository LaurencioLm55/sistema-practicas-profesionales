package com.sistemapracticasprofesional.logic.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import com.sistemapracticasprofesional.logic.validators.ProjectAttendantValidator;

public class ProjectAttendantValidatorTest {

    private ProjectAttendantValidator validator;
    
    
    @BeforeEach
    public void setUp(){
        validator = new ProjectAttendantValidator();
    }

    @Test
    public void nameTest(){

        boolean expectedResult = true;
        boolean result = validator.isNameProjectAttendant("Leonardo Hernández Hernández");
        
        assertEquals(expectedResult, result);

    }

    @Test
    public void positionProjectAttendantTest(){

        boolean expectedResult = true;
        boolean result = validator.isPositionProjectAttendant("Jefe de tecnologias");

        assertEquals(expectedResult, result);

    }

    @Test
    public void emailProjectAttendantTest (){

        boolean expectedResult = true;
        boolean result = validator.isEmailProjectAttendant("Leonardo1331@gmail.com");

        assertEquals(expectedResult, result);


    }

}
