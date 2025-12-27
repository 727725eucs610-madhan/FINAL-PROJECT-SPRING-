package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.UserRoleMapping;
import java.util.List;

@Repository
public interface UserRoleMappingRepo extends JpaRepository<UserRoleMapping, Long> {
    @Query("SELECT urm FROM UserRoleMapping urm WHERE urm.user.id = :userId")
    List<UserRoleMapping> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT urm FROM UserRoleMapping urm WHERE urm.user.id = :userId AND urm.role.id = :roleId")
    UserRoleMapping findByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
