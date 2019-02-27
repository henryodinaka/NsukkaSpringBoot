package nsk.cath.com.service.contact;

import nsk.cath.com.model.contact.Deanery;
import nsk.cath.com.repo.contact.DeaneryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeaneryService {
    private DeaneryRepo deaneryRepo;

    @Autowired
    public void setDeaneryRepo(DeaneryRepo deaneryRepo) {
        this.deaneryRepo = deaneryRepo;
    }

    public Deanery findByName(String name)
    {
        return deaneryRepo.findByName(name);
    }

    public Deanery findByDeaneryCode(String deaneryCode)
    {
        return deaneryRepo.findByDeaneryCode(deaneryCode);
    }
    public Page<Deanery> findAllByDiocese(Long diocese, Pageable pageable)
    {
        return deaneryRepo.findAllByDiocese(diocese , pageable);
    }
    public List<Deanery> findAllByDiocese(Long diocese)
    {
        return deaneryRepo.findAllByDiocese(diocese);
    }
}
