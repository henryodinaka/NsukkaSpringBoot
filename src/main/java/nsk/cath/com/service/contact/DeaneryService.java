package nsk.cath.com.service.contact;

import nsk.cath.com.model.contact.Deanery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeaneryService {
    private DeaneryService deaneryService;

    @Autowired
    public void setDeaneryService(DeaneryService deaneryService) {
        this.deaneryService = deaneryService;
    }

    public Deanery findByName(String name)
    {
        return deaneryService.findByName(name);
    }

    public Deanery findByDeaneryCode(String deaneryCode)
    {
        return deaneryService.findByDeaneryCode(deaneryCode);
    }
    public Page<Deanery> findAllByDiocese(Long diocese, Pageable pageable)
    {
        return deaneryService.findAllByDiocese(diocese , pageable);
    }
    public List<Deanery> findAllByDiocese(Long diocese)
    {
        return deaneryService.findAllByDiocese(diocese);
    }
}
