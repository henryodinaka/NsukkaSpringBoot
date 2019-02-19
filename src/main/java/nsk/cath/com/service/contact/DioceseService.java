package nsk.cath.com.service.contact;

import nsk.cath.com.model.contact.Diocese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DioceseService {
    private DioceseService dioceseService;

    @Autowired
    public void setDioceseService(DioceseService dioceseService) {
        this.dioceseService = dioceseService;
    }

    public Diocese findAllByName(String name)
    {
        return dioceseService.findAllByName(name);
    }

    public Diocese findAllById(String id)
    {
        return dioceseService.findAllById(id);
    }

    public Page<Diocese> findAllByState(String stateId, Pageable pageable)
    {
        return dioceseService.findAllByState(stateId,pageable);
    }
    public List<Diocese> findAllByState(String stateId)
    {
        return dioceseService.findAllByState(stateId);
    }
}
