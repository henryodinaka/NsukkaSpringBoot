package nsk.cath.com.service.contact;

import nsk.cath.com.dto.ParishRequest;
import nsk.cath.com.dto.UserDetail;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.model.User;
import nsk.cath.com.model.contact.Deanery;
import nsk.cath.com.model.contact.Parish;
import nsk.cath.com.repo.contact.ParishRepo;
import nsk.cath.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParishService {
    private ParishRepo parishRepo;
    private UserService userService;
    private DeaneryService deaneryService;

    @Autowired
    public void setDeaneryService(DeaneryService deaneryService) {
        this.deaneryService = deaneryService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setParishRepo(ParishRepo parishRepo) {
        this.parishRepo = parishRepo;
    }
    public  Page<Parish> findAll(Pageable pageable)
    {
        return parishRepo.findAll(pageable);
    }
    public Parish findByName(String name)
    {
        return parishRepo.findByName(name);
    }

    public Parish save(Parish parish)
    {
        return parishRepo.save(parish);
    }
    public Parish findById(Long id)
    {
        return parishRepo.getOne(id);
    }

    public Page<Parish> findAllByDeanery(Long deaneryId, Pageable pageable)
    {
        return parishRepo.findAllByDeanery(deaneryId,pageable);
    }

    public List<Parish> findAllByDeanery(Long deaneryId)
    {
        return parishRepo.findAllByDeanery(deaneryId);
    }

    public Parish setup(ParishRequest request, UserDetail userDetail) throws NSKException {
        User operator = userService.findById(userDetail.getUserId());
        if (operator==null)
            throw new NSKException("Please login and try again","404","404");
        Parish parish =null;

        Deanery deanery = deaneryService.findOne(request.getDeaneryId());
        if (!request.isUpdate())
            parish=new Parish();
        parish = generate(request, operator, parish,deanery);

        return this.save(parish);
    }

    private Parish generate(ParishRequest request, User operator, Parish parish,Deanery deanery) throws NSKException {
        parish.setParishCode(request.getParishCode());
        parish.setName(request.getName());
        if (request.isUpdate())
        {
            parish = parishRepo.getOne(request.getId());
            if (parish ==null)
                throw new NSKException("Parish selected does not exist","404","404");
            parish.setUpdatedBy(new User(operator.getId(),operator.getEmail()));

        }else
        {
            parish.setDeanery(new Deanery(deanery.getId(),deanery.getName(),deanery.getDeaneryCode()));
            parish.setCreatedBy(new User(operator.getId(),operator.getEmail()));

        }
        return parish;
    }
}
