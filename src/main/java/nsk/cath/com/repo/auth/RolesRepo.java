package nsk.cath.com.repo.auth;

import nsk.cath.com.enums.RoleName;
import nsk.cath.com.enums.RoleType;
import nsk.cath.com.model.auth.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface RolesRepo extends JpaRepository<Role, Long> {

    @Query("select r from Role r")
    Page<Role> getAll(Pageable pageable);

    @Query("select r from Role r where r.roleType =:roleType")
    List<Role> getAll(@Param("roleType") RoleType roleType);

    @Query("select r from Role r where r.name =:name ")
    Role getByName(@Param("name") RoleName name);

    @Query("select r from Role r where r.id = :id ")
    Role getById(@Param("id") Long id);

    @Query("select r from Role r where r.name = :name and r.id <> :id")
    Role getByNameAndNotId(@Param("name") RoleName name, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Role r SET r.activated = CASE r.activated WHEN true THEN false ELSE true END WHERE r.id =:id")
    int toggle(@Param("id") Long id);

}
