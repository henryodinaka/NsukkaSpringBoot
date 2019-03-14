package nsk.cath.com.service.auth;

import nsk.cath.com.dto.auth.RoleRequest;
import nsk.cath.com.enums.RoleName;
import nsk.cath.com.enums.RoleType;
import nsk.cath.com.errorHandler.ErrorDetails;
import nsk.cath.com.model.auth.Role;
import nsk.cath.com.repo.auth.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RolesRepo rolesRepo;

    @Autowired
    public void setRolesRepo(RolesRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }
    public Page<Role> getAll(Pageable pageable)
    {
        return rolesRepo.getAll(pageable);
    }
    public List<Role> getAll(RoleType roleType)
    {
        return rolesRepo.getAll(roleType);
    }
   public Role getByName(RoleName name)
   {
       return rolesRepo.getByName(name);
   }

    public Role getById(Long id)
    {
        return rolesRepo.getById(id);
    }
    public Role getByNameAndNotId(RoleName name,Long id)
    {
        return rolesRepo.getByNameAndNotId(name,id);
    }
    public int toggle(Long id)
    {
        return rolesRepo.toggle(id);
    }

    public Role save(Role role){
        return rolesRepo.save(role);
    }
    public String validate(RoleRequest role, boolean isUpdate, Long id){

        Role existingRole = null;
        if (isUpdate){
            if (id == null){
                return "Role id is not provided";
            }

            existingRole = getByNameAndNotId(role.getName(), id);
        }else {
            existingRole = getByName(role.getName());
        }

        if (existingRole != null){
            return "Role name '"+role.getName()+"' already exist";
        }

        return null;
    }

    public ResponseEntity setUp(RoleRequest role) {
        String errorResult = validate(role, false, null);
        if (errorResult != null) {
            return ResponseEntity.badRequest().body(new ErrorDetails(errorResult));
        }

        Role newRole = new Role();
        newRole.setDescription(role.getDescription());
        newRole.setName(role.getName());
        newRole.setRoleType(role.getRoleType());
        newRole = save(newRole);

        return ResponseEntity.ok(newRole);
    }
}
