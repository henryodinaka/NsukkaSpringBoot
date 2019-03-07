package nsk.cath.com.controller;

import lombok.extern.slf4j.Slf4j;
import nsk.cath.com.dto.UserRequest;
import nsk.cath.com.enums.Status;
import nsk.cath.com.errorHandler.ErrorDetails;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Slf4j
@Path("/user")
@Consumes(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserController {
    @Autowired
    private UserService userService;
    @POST
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest,@RequestParam boolean isInNIgeria)
    {
        try {
            userService.setup(userRequest,isInNIgeria,false);
        } catch (NSKException e) {
            log.error("NSKEscption caught ",e);
            ErrorDetails.setUpErrors("User creation failed", Arrays.asList(e.getMessage()),e.getCode());
        }
        return ResponseEntity.status(200).body("");
    }
    @PUT
    public ResponseEntity<?> update(@RequestBody UserRequest userRequest,@RequestParam boolean isInNIgeria)
    {
        try {
            userService.setup(userRequest,isInNIgeria,true);
        } catch (NSKException e) {
            log.error("NSKEscption caught ",e);
            ErrorDetails.setUpErrors("User creation failed", Arrays.asList(e.getMessage()),e.getCode());
        }
        return ResponseEntity.status(200).body("");
    }

    @GET
    public ResponseEntity<?> getById(@RequestParam Long userId)
    {
        return ResponseEntity.ok(userService.findById(userId));
    }
    @GET
    public ResponseEntity<?> getAll(@RequestParam Long userId,@RequestParam int pageNum, @RequestParam int pageSize)
    {
        return ResponseEntity.ok(userService.getAllUsers(new PageRequest(pageNum,pageSize)));
    }
    @GET
    public ResponseEntity<?> getAllByParish(@RequestParam Long parishId,@RequestParam int pageNum, @RequestParam int pageSize)
    {
        return ResponseEntity.ok(userService.getAllUsersByParish(parishId,new PageRequest(pageNum,pageSize)));
    }
    @GET
    public ResponseEntity<?> getAllByStatus(@RequestParam Status status, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        return ResponseEntity.ok(userService.getAllUsersByStatus(status,new PageRequest(pageNum,pageSize)));
    }
    @GET
    public ResponseEntity<?> getAllByEmailAddress(@RequestParam String emailAddress)
    {
        return ResponseEntity.ok(userService.findUserByEmailAddress(emailAddress));
    }
    @GET
    public ResponseEntity<?> getAllByPhone(@RequestParam String phoneNumber)
    {
        return ResponseEntity.ok(userService.findByPhoneNumber(phoneNumber));
    }
    @GET
    public ResponseEntity<?> countByParish(@RequestParam Long parishId)
    {
        return ResponseEntity.ok(userService.countUserByParishId(parishId));
    }
}
