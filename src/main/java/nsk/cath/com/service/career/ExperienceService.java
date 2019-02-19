package nsk.cath.com.service.career;

import nsk.cath.com.model.career.Experience;
import nsk.cath.com.repo.career.ExperienceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {
    private ExperienceRepo experienceRepo;

    @Autowired
    public void setExperienceRepo(ExperienceRepo experienceRepo) {
        this.experienceRepo = experienceRepo;
    }

    public List<Experience> findByUser(Long userId)
    {
        return experienceRepo.findByUser(userId);
    }

    public Page<Experience> findByJobTitle(String jobTitle, Pageable pageable)
    {
        return experienceRepo.findByJobTitle(jobTitle,pageable);
    }

    public Page<Experience> findByCompany(String company, Pageable pageable)
    {
        return experienceRepo.findByCompany(company,pageable);
    }
}
