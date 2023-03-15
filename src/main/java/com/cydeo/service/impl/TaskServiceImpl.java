package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        if(taskDTO.getAssignedDate()==null)
            taskDTO.setAssignedDate(LocalDate.now());
        if(taskDTO.getTaskStatus()==null)
            taskDTO.setTaskStatus(Status.OPEN);
        if(taskDTO.getId()==null)
            taskDTO.setId(new Random().nextLong());
        return super.save(taskDTO.getId(),taskDTO);
    }

    @Override
    public TaskDTO findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void update(TaskDTO taskDTO) {
        if(taskDTO.getAssignedDate()==null)
            taskDTO.setAssignedDate(LocalDate.now());
        if(taskDTO.getTaskStatus()==null)
            taskDTO.setTaskStatus(Status.OPEN);
        super.update(taskDTO.getId(),taskDTO);
    }


}
