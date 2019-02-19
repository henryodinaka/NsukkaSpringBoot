package nsk.cath.com.service.contact;

import nsk.cath.com.model.contact.State;
import nsk.cath.com.repo.contact.StateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    private StateRepo stateRepo;

    @Autowired
    public void setStateRepo(StateRepo stateRepo) {
        this.stateRepo = stateRepo;
    }
   public long getCountByName(String name,String countryName)
    {
        return stateRepo.getCountByName(name,countryName);
    }

    public long getCountByNameAndNotId(String name, Long id)
    {
        return stateRepo.getCountByNameAndNotId(name,id);
    }

    public List<State> getStatesByCountry(Long id)
    {
        return stateRepo.getStatesByCountry(id);
    }

    public Page<State> getStatesByCountry(Long id, Pageable pageable)
    {
        return stateRepo.getStatesByCountry(id,pageable);
    }
}
