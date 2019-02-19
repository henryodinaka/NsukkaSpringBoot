package nsk.cath.com.service.auth;

import nsk.cath.com.enums.RoleName;
import nsk.cath.com.enums.RoleType;
import nsk.cath.com.model.auth.Role;
import nsk.cath.com.repo.auth.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
}
