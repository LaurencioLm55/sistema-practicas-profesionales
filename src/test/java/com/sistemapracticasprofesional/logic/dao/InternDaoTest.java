package com.sistemapracticasprofesional.logic.dao;

import org.junit.jupiter.api.Test;

import com.sistemapracticasprofesional.logic.dto.InternDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class InternDaoTest {
    
    private InternDao internDao;

    public InternDaoTest() {
    }

    @BeforeEach
    void initializaDao(){

        internDao = new InternDao();
        
    }

    @Test
    public void testInsertInternSuccess() {

        boolean expetedResult = true;

        InternDto internDto = new InternDto("S24060080", 23, "Rodrigo Montoya Herrera", "No", "Masculino", "Ingenieria de Software");
        boolean realResult = internDao.insertIntern(internDto);

        assertEquals(expetedResult, realResult);

    }

    @Test
    public void testUpdateInternSuccess() {

        boolean expetedResult = true;

        InternDto internDto = new InternDto("S24060080", 23, "Sebastián Vargas Ríos", "No", "Masculino", "Ingenieria de Software");
        boolean realResult = internDao.updateIntern(internDto);

        assertEquals(expetedResult, realResult);

    }

    @Test
    public void testGetInternByMatriculaSuccess() {

        InternDto expetedResult = new InternDto("S24060080", 23, "Sebastián Vargas Ríos", "No", "Masculino", "Ingenieria de Software");
        InternDto realResult = internDao.getInternByStudentId("S24060080");

        assertEquals(expetedResult, realResult);

    }

    @Test
    public void testGetAllInternsSuccess() {

        InternDto internDto0 = new InternDto("S24060080", 23, "Sebastián Vargas Ríos", "No", "Masculino", "Ingenieria de Software");
        InternDto internDto1 = new InternDto("S47382910", 23, "Valeria Moreno Ríos", null, "Femenino", "Ingenieria de software");
        InternDto internDto2 = new InternDto("S83041562", 24, "Diego Hernández Cruz", "Nahuatl", "Masculino", "Ingenieria de software");
        InternDto internDto3 = new InternDto("S29174803", 22, "Sofía Ramírez Luna", "Totonaco", "Femenino", "Ingenieria de software");
        InternDto internDto4 = new InternDto("S65920347", 25, "Andrés Vázquez Torres", null, "Masculino", "Ingenieria de software");
        
        List<InternDto> expetedResult = new ArrayList<>();

        expetedResult.add(internDto0);
        expetedResult.add(internDto1);
        expetedResult.add(internDto2);
        expetedResult.add(internDto3);
        expetedResult.add(internDto4);

        List<InternDto> realResult = internDao.getAllInterns();

        assertEquals(expetedResult, realResult);

    }

    @Test
    public void testDeleteInternSuccess() {

        boolean expetedResult = true;
        boolean result = internDao.deleteIntern("S24060080");

        assertEquals(expetedResult, result);

    }

    @Test
    public void testAssignProjectSuccess(){

        boolean expetedResult = true;
        boolean result = internDao.assignProject("S24013248", 1213);

        assertEquals(expetedResult, result);
        
    }
    
}
