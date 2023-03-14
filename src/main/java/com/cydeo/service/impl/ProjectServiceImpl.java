package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {

        if(projectDTO.getStatus()==null)
            projectDTO.setStatus(Status.OPEN);

        return super.save(projectDTO.getProjectCode(), projectDTO);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void update(ProjectDTO object) {

        if(object.getStatus()==null)
            object.setStatus(findById(object.getProjectCode()).getStatus());
        super.update(object.getProjectCode(), object);
    }


    @Override
    public void complete(ProjectDTO projectDTO) {
        projectDTO.setStatus(Status.COMPLETE);
    }
}
