package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }


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

    //@Override
//    public List<ProjectDTO> getCountedListOfProjectDto(UserDTO manager) {
//
//        List<ProjectDTO> projectDTOList=findAll().stream().
//                filter(project->project.getAssignedManager().equals(manager))
//                .map(project->{
//
//                    List<TaskDTO> taskListByManager=taskService.findTasksByManager(manager);
//
//                    int completeTaskCounts= (int) taskListByManager.stream().filter(t->t.getProjectDTO().equals(project) && t.getTaskStatus()==Status.COMPLETE).count();
//                    int unfinishedTaskCounts=(int) taskListByManager.stream().filter(t->t.getProjectDTO().equals(project) && t.getTaskStatus()!=Status.COMPLETE).count();;
//
//                    project.setUnfinishedTaskCounts(unfinishedTaskCounts);
//                    project.setCompleteTaskCounts(completeTaskCounts);
//
//                    return project;
//
//                })
//                .collect(Collectors.toList());
//
//        return projectDTOList ;
//    }
}
