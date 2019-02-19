package nsk.cath.com.service.career;

import nsk.cath.com.model.career.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private SkillService skillService;

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }
    public List<Skill> findByUser(Long userId)
    {
        return skillService.findByUser(userId);
    }
}
