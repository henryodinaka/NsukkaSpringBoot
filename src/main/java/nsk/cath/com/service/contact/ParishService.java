package nsk.cath.com.service.contact;

import nsk.cath.com.model.contact.Parish;
import nsk.cath.com.repo.contact.ParishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParishService {
    private ParishRepo parishRepo;

    @Autowired
    public void setParishRepo(ParishRepo parishRepo) {
        this.parishRepo = parishRepo;
    }
    public Parish findByName(String name)
    {
        return parishRepo.findByName(name);
    }

    public Parish findById(String id)
    {
        return parishRepo.findById(id);
    }

    public Page<Parish> findAllByDeanery(String deaneryId, Pageable pageable)
    {
        return parishRepo.findAllByDeanery(deaneryId,pageable);
    }

    public List<Parish> findAllByDeanery(String deaneryId)
    {
        return parishRepo.findAllByDeanery(deaneryId);
    }
}
