package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;

import java.util.List;


public interface TaskService extends CrudService<TaskDTO,Long>{
    List<TaskDTO> findTasksByManager(UserDTO manager);
    List<TaskDTO> findAllTasksByIsNot(Status status);
    void updateStatus(TaskDTO taskDTO);
}
