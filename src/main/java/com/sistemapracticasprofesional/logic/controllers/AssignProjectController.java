package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.dao.ProjectDao;
import java.util.List;

public class AssignProjectController {

    public List<InternDto> getListIntern(){

        InternDao internDao = new InternDao();
        List<InternDto> listInterns = internDao.getInternsActive();
        
        return listInterns;

    }

    public List<ProjectDto> getListProjects(){

        ProjectDao projectDao = new ProjectDao();
        List <ProjectDto> listProjects = projectDao.getAllProjects();

        return listProjects;

    }

    public boolean assingProject( String studentId, int projectId ) 
    throws BusinessLogicException {

        boolean result = false;

        InternDao internDao = new InternDao();

        try{

            internDao.assignProject( studentId, projectId );

            if( internDao.isProjectAssignedIntern( studentId, projectId ) ){

                result = true;

            } else {

                throw new BusinessLogicException("No se pudo asignar el proyecto");

            }
            
        } catch ( DaoException e ){

            throw new BusinessLogicException("Error al asignar proyecto", e);

        }
        
        return result;

    }
}
