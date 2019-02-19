package nsk.cath.com.service.career;

import nsk.cath.com.enums.Qualification;
import nsk.cath.com.model.career.Education;
import nsk.cath.com.repo.career.EducationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private EducationRepo educationRepo;

    @Autowired
    public void setEducationRepo(EducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }

    public Page<Education> findBySchool(String school, Pageable pageable)
    {
        return educationRepo.findBySchool(school,pageable);
    }

    public Page<Education> findByCourse(String course,Pageable pageable)
    {
        return educationRepo.findByCourse(course,pageable);
    }

    public List<Education> findByUser(Long userId)
    {
        return educationRepo.findByUser(userId);
    }

   public Page<Education> findByQualification(Qualification qualification, Pageable pageable)
    {
        return educationRepo.findByQualification(qualification,pageable);
    }
}
