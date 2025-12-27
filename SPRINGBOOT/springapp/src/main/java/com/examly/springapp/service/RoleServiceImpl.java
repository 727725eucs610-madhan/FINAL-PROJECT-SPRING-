package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.repository.RoleRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examly.springapp.model.Role;
@Service
public class RoleServiceImpl implements RoleService {
    

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public Role addRole(Role role) {
        return roleRepo.save(role);
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }
    @Override
    public Role getRoleById(Long id) {
        return roleRepo.findById(id).orElse(null);
    }
    @Override
    public Role updateRole(Long id,Role role) {
        Role existing = roleRepo.findById(id).orElse(null);
        if(existing != null) {
            existing.setRoleName(role.getRoleName());
            return roleRepo.save(existing);
        }
        return null;
    }
    @Override
    public Page<Role> getRolesWithPagination(Pageable pageable) {
        return roleRepo.findAll(pageable);
    }
    @Override
    public void deleteRole(Long id)
    {
        roleRepo.deleteById(id);
    }
}
