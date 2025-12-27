package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Role;
import com.examly.springapp.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController { 
@Autowired
private RoleService roleService;
@GetMapping()
public ResponseEntity<List<Role>> getAllRoles()  {
    return ResponseEntity.ok(roleService.getAllRoles());
}
@PostMapping
public ResponseEntity<Role> addRole(@RequestBody Role role)
{
    return new ResponseEntity<>(roleService.addRole(role), HttpStatus.CREATED);
}
@GetMapping("/{id}")
public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
    Role role=roleService.getRoleById(id);
    if(role == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(role);
}
@PutMapping("/{id}")
public ResponseEntity<Role> updateRole(@PathVariable Long id,@RequestBody Role role)
{
    Role updated = roleService.updateRole(id,role);
    if(updated == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(updated);
}
@DeleteMapping("/{id}")
    public ResponseEntity <String> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("role deleted successfully");
    }
@GetMapping("/page/{page}/{size}")
public ResponseEntity<Page<Role>> getRolesWithPagination(
    @PathVariable int page,
    @PathVariable int size,
    @RequestParam(required = false) String sortBy,
    @RequestParam(required = false) String direction) 
    {
        Pageable pageable;
        if(sortBy != null && direction != null) {
        Sort sort = direction.equalsIgnoreCase("desc")
        ? Sort.by(sortBy).descending()
        : Sort.by(sortBy).ascending();
         pageable = PageRequest.of(page,size,sort);
        }
        else
        {
            pageable = PageRequest.of(page,size);
        }
        return ResponseEntity.ok(roleService.getRolesWithPagination(pageable));
    }
}