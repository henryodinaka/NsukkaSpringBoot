package nsk.cath.com.service.contact;

import nsk.cath.com.dto.BranchRequest;
import nsk.cath.com.dto.UserDetail;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.model.User;
import nsk.cath.com.model.contact.Branch;
import nsk.cath.com.model.contact.Country;
import nsk.cath.com.model.contact.State;
import nsk.cath.com.repo.contact.BranchRepo;
import nsk.cath.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private StateService stateService;
    public Branch processRequest(UserDetail userDetail, BranchRequest request) throws NSKException {
        User operator = userService.findById(userDetail.getUserId());
        if (operator==null)
            throw new NSKException("Please login and try again","401","401");
        Branch branch = null;

        if (request.isUpdate())
        {
            branch = getOne(request.getId());
            if (branch ==null)
                throw new NSKException("The branch does not exist","404","404");
        }else {
            branch = new Branch();
        }
        branch = generate(operator,request,branch);
        return this.save(branch);

    }
    public Branch save(Branch branch)
    {
        return branchRepo.save(branch);
    }
    private Branch generate(User operator,BranchRequest request, Branch branch) throws NSKException {

        branch.setAddress(request.getAddress());
        branch.setCity(request.getCity());
        branch.setName(request.getName());
        if (request.isUpdate())
        {
            branch.setUpdatedBy(new User(operator.getId(),operator.getEmail()));
    }else
    {
        State state = stateService.get(request.getId());
        if (state ==null)
            throw new NSKException("Invalid state selected","404","404");
            branch.setState(new State(state.getId(),state.getName()));
            branch.setCreatedBy(new User(operator.getId(),operator.getEmail()));
        }
        return branch;
    }
    public Branch getOne(Long id)
    {
        return branchRepo.getOne(id);
    }
    public Page<Branch> findAll(Pageable pageable)
    {
        return branchRepo.findAll(pageable);
    }

    public Page<Branch> findAllByState( Long stateId, Pageable pageable)
    {
        return branchRepo.findAllByState(stateId,pageable);
    }
    public List<Branch> findAllByState( Long stateId)
    {
        return branchRepo.findAllByState(stateId);
    }
//    public Page<Branch> findAllByCountry( Long countryId,Pageable pageable)
//    {
//        return branchRepo.findAllByCountry(countryId,pageable);
//    }
}
