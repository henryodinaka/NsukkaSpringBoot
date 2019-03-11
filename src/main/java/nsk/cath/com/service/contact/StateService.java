package nsk.cath.com.service.contact;

import nsk.cath.com.dto.StateRequest;
import nsk.cath.com.model.contact.State;
import nsk.cath.com.repo.contact.StateRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private static Logger LOG = LoggerFactory.getLogger(StateService.class);

    private StateRepo stateRepo;

    private CountryService countryService;

    @Autowired
    public void setStateRepo(StateRepo stateRepo) {
        this.stateRepo = stateRepo;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public Page<State> get(Pageable pageable){
        return stateRepo.findAll(pageable);
    }

    public List<State> get(){
        return stateRepo.findAll();
    }

    public State get(Long id){
        return stateRepo.getOne(id);
    }
    public State save(State state){
        return stateRepo.save(state);
    }

    public int getByName(String name){
        return stateRepo.getCountByName(name);
    }

    public int getByNameAndNotId(String name, Long id){
        return stateRepo.getCountByNameAndNotId(name, id);
    }

    private String validate(StateRequest request, boolean isUpdate, Long id){

        int count = 0;
        if (isUpdate){
            if (id == null){
                return "State id is not provided";
            }

            count = getByNameAndNotId(request.getName(), id);
        }else {
            count = getByName(request.getName());
        }

        if (count > 0){
            return "State name '"+request.getName()+"' already exist";
        }

        return null;
    }

    private State generate(State state, StateRequest request){
        state.setName(request.getName());
        state.setCountry(countryService.getOne(request.getId()));
        return state;
    }

    public List<State> getStatesByCountry(Long id){
        return stateRepo.getStatesByCountry(id);
    }
}
