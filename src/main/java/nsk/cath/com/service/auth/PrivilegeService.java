package nsk.cath.com.service.auth;

import nsk.cath.com.model.auth.Privilege;
import nsk.cath.com.repo.auth.PrivilegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PrivilegeService {
    private PrivilegeRepo privilegeRepo;

    @Autowired
    public void setPrivilegeRepo(PrivilegeRepo privilegeRepo) {
        this.privilegeRepo = privilegeRepo;
    }
    public Set<Privilege> getAllByIdAndActivated( boolean activated,List<Long> ids)
    {
        return privilegeRepo.getAllByIdAndActivated(ids);
    }

    public Page<Privilege> getAllByActivated( boolean activated,Pageable pageable){
        return privilegeRepo.getAllByActivated(activated,pageable);
    }

   public Page<Privilege> getAll(Pageable pageable)
   {
       return privilegeRepo.getAll(pageable);
   }

    public List<Privilege> getAllByActivated( boolean activated)
    {
        return privilegeRepo.getAllByActivated(activated);
    }

    public List<Privilege> getAll()
    {
        return privilegeRepo.getAll();
    }

    public int toggle(Long id)
    {
        return privilegeRepo.toggle(id);
    }
}
