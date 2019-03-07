package nsk.cath.com.service.contact;

import nsk.cath.com.dto.LgaRequest;
import nsk.cath.com.model.contact.Lga;
import nsk.cath.com.model.contact.State;
import nsk.cath.com.repo.contact.LgaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LgaService {

    private static Logger LOG = LoggerFactory.getLogger(LgaService.class);

    private LgaRepo lgaRepo;

    private StateService stateService;

    @Autowired
    public void setLgaRepo(LgaRepo lgaRepo) {
        this.lgaRepo = lgaRepo;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    public Page<Lga> getAll(Long stateId, Pageable pageable){
        return lgaRepo.getLgasByState(stateId, pageable);
    }

    public List<Lga> getAll(Long stateId){
        return lgaRepo.getLgasByState(stateId);
    }

    public Lga get(Long id){
        return lgaRepo.getOne(id);
    }

    public Lga get(Long id, Long stateId){
        return lgaRepo.getLga(id, stateId);
    }

    public Lga save(Lga lga){
        return lgaRepo.save(lga);
    }

    public long getByName(String name){
        return lgaRepo.getCountByName(name);
    }

    public long getByName(String name, Long id){
        return lgaRepo.getCountByName(name, id);
    }

    public List<String> getAllNames(){
        return lgaRepo.getAllNames();
    }
    public List<String> getAllCodes(){
        return lgaRepo.getAllCodes();
    }

    public String validate(LgaRequest request, boolean isUpdate, Long id){

        long count = 0;
        if (isUpdate){
            if (id == null){
                return "Lga id is not provided";
            }

            count = getByName(request.getName(), id);
        }else {
            count = getByName(request.getName());
        }

        if (count > 0){
            return "Lga name '"+request.getName()+"' already exist";
        }

        State state = stateService.get(request.getStateId());
        if (state == null){
            return "Unknown state provided";
        }

        return null;
    }

    public Lga generate(Lga lga, LgaRequest request){
        lga.setName(request.getName());
        lga.setState(stateService.get(request.getStateId()));
        return lga;
    }

    public List<Lga> getAll(){
        return lgaRepo.getAll();
    }

    public Lga getFirstLgaInStateName(String stateName){
        List<Lga> lgas = lgaRepo.getAnyLgaInStateLike("%" + stateName + "%", new PageRequest(0, 1));
        int count = lgas == null ? 0 : lgas.size();
        if (count < 1){
            return new Lga(Long.valueOf("509"));
        }
        return lgas.get(0);
    }
}