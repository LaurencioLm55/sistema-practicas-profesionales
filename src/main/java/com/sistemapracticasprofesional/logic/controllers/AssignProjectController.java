package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.dao.ProjectDao;

import java.util.ArrayList;
import java.util.List;

public class AssignProjectController {

    public List<InternDto> getListIntern() throws BusinessLogicException{

        InternDao internDao = new InternDao();
        List<InternDto> listInterns = new ArrayList<>();

        try {

            listInterns.addAll(internDao.getInternsActive());

        } catch (DaoException e){

            throw new BusinessLogicException("No se pudo recuperar la" + 
            "lista de practicantes");

        }
        
        return listInterns;

    }

    public List<ProjectDto> getListProjects() throws BusinessLogicException {

        ProjectDao projectDao = new ProjectDao();
        List <ProjectDto> listProjects = new ArrayList<>();

        try{ 

           listProjects.addAll(projectDao.getAllProjects());

        } catch (DaoException e) {

            throw new BusinessLogicException("No se pudo obtener la" +
            "lista de projectos");

        }

        return listProjects;

    }

    public boolean assingProject(String studentId, int projectId) 
    throws BusinessLogicException {

        boolean result = false;

        InternDao internDao = new InternDao();

        try{

            internDao.assignProject(studentId, projectId);

            if(internDao.isProjectAssignedIntern(studentId, projectId)){

                result = true;

            }
            
        } catch ( DaoException e ){

            throw new BusinessLogicException("Error al asignar proyecto", e);

        }
        
        return result;

    }
}
