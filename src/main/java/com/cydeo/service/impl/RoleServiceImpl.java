package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl extends AbstractMapService <RoleDTO,Long> implements RoleService {


    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        return super.save(roleDTO.getId(),roleDTO);
    }

    @Override
    public RoleDTO findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void update(RoleDTO object) {
        super.update(object.getId(),object);
    }
}
