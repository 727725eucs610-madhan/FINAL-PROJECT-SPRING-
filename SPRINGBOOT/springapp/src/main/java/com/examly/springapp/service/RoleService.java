package com.examly.springapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examly.springapp.model.Role;

public interface RoleService {
Role addRole(Role role);
List<Role> getAllRoles();
Role getRoleById(Long id);
Role updateRole(Long id,Role role);
Page<Role> getRolesWithPagination(Pageable pageable);
void deleteRole(Long id);
}
