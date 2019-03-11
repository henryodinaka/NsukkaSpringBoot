package nsk.cath.com.controller;

import lombok.extern.slf4j.Slf4j;
import nsk.cath.com.dto.BranchRequest;
import nsk.cath.com.dto.UserDetail;
import nsk.cath.com.enums.Constants;
import nsk.cath.com.errorHandler.ErrorDetails;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.model.contact.Branch;
import nsk.cath.com.service.contact.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/branch")
@Consumes(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class BranchController {
    @Autowired
    private BranchService branchService;
    @GET
    public ResponseEntity<?> findAll(@RequestParam int pageNum,  int pageSize)
    {
        return ResponseEntity.ok(branchService.findAll(new PageRequest(pageNum,pageSize)));
    }
//    @GET
//    @Path("/country")
//    public ResponseEntity<?> findAllByCountry(@RequestParam Long counrtyId , @RequestParam int pageNum,  int pageSize)
//    {
//        return ResponseEntity.ok(branchService.findAllByCountry(counrtyId,new PageRequest(pageNum,pageSize)));
//    }
    @GET
    @Path("/state")
    public ResponseEntity<?> findAllByState(@RequestParam Long stateId)
    {
        return ResponseEntity.ok(branchService.findAllByState(stateId));
    }
    @GET
    @Path("/state")
    public ResponseEntity<?> findAllByState(@RequestParam Long stateId,@RequestParam int pageNum,  int pageSize)
    {
        return ResponseEntity.ok(branchService.findAllByState(stateId,new PageRequest(pageNum,pageSize)));
    }

    @POST
    public ResponseEntity<?> createBranch(@RequestBody BranchRequest request,@ApiIgnore @RequestAttribute(Constants.USER_DETAIL)UserDetail userDetail)
    {
        Branch branch = null;
        try {
            branch = branchService.processRequest(userDetail, request);
            return ResponseEntity.ok(branch);
        } catch (NSKException e) {
            e.printStackTrace();
            return ErrorDetails.setUpErrors("Creation Failed", Arrays.asList(e.getMessage()),e.getCode());
        }
    }
}
