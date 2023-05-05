package com.bank.app.service;


import com.bank.app.dto.RoleDTO;

import java.util.List;


public interface RoleService {

    public RoleDTO createRole(RoleDTO roleDTO);

    public RoleDTO getRoleById(Long id);

    public List<RoleDTO> getAllRole();

    public RoleDTO updateRole(RoleDTO roleDTO);

    public void deleteRole(Long id);


}
