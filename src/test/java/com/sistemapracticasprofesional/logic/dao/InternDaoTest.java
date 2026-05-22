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
        internDao.assignProject("S24014080", 1);
        
    }

    @Test
    public void testGetInternsActiveSuccess(){

        List<InternDto> expetedResult = new ArrayList<>();

        InternDto internDto1 = new InternDto();
        InternDto internDto2 = new InternDto();
        InternDto internDto3 = new InternDto();

        internDto1.setStudentId("S23014080");
        internDto2.setStudentId("S24014080");
        internDto3.setStudentId("S24014581");

        internDto1.setName("Leonardo Hernández Hernández");
        internDto2.setName("Ana Lopez Beltran");
        internDto3.setName("Juan Martin España");

        expetedResult.add(internDto1);
        expetedResult.add(internDto2);
        expetedResult.add(internDto3);

        List<InternDto> result = internDao.getInternsActive();
        
        assertEquals(expetedResult, result);

    }

    @Test
    public void testIsProjectAssignedInetern(){
        boolean expetedResult = true;
        boolean result = internDao.isProjectAssignedIntern("S23014080", 1213);

        assertEquals(expetedResult, result);
        
    }
    
}
