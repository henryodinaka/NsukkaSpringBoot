package nsk.cath.com.controller;

import lombok.extern.slf4j.Slf4j;
import nsk.cath.com.dto.AssignRoleRequest;
import nsk.cath.com.dto.UserDetail;
import nsk.cath.com.dto.UserRequest;
import nsk.cath.com.enums.Constants;
import nsk.cath.com.enums.Status;
import nsk.cath.com.errorHandler.ErrorDetails;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.model.User;
import nsk.cath.com.service.UserService;
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
@RequestMapping("/user")
@Consumes(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest,@RequestParam boolean isInNIgeria)
    {
        try {
            return userService.setup(userRequest,isInNIgeria,false);
        } catch (NSKException e) {
            log.error("NSKEscption caught ",e);
            return ErrorDetails.setUpErrors("User creation failed", Arrays.asList(e.getMessage()),e.getCode());
        }
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserRequest userRequest,@RequestParam boolean isInNIgeria)
    {
        try {
           return userService.setup(userRequest,isInNIgeria,true);
        } catch (NSKException e) {
            log.error("NSKEscption caught ",e);
           return ErrorDetails.setUpErrors("User creation failed", Arrays.asList(e.getMessage()),e.getCode());
        }
    }
    @PutMapping("/assign")
    public ResponseEntity<?>assignRole(@RequestBody AssignRoleRequest roleRequest, @ApiIgnore @RequestAttribute(Constants.USER_DETAIL)UserDetail userDetail)
    {
        User user = null;
        try {
            user = userService.assignRole(userDetail, roleRequest);
            return ResponseEntity.ok(user);
        } catch (NSKException e) {
            return ErrorDetails.setUpErrors("Update Failed",Arrays.asList(e.getMessage()),e.getCode());
        }
    }
    @GetMapping("/id")
    public ResponseEntity<?> getById(@RequestParam Long userId)
    {
        return ResponseEntity.ok(userService.findById(userId));
    }
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam Long userId,@RequestParam int pageNum, @RequestParam int pageSize)
    {
        return ResponseEntity.ok(userService.getAllUsers(new PageRequest(pageNum,pageSize)));
    }
    @GetMapping("/parish")
    public ResponseEntity<?> getAllByParish(@RequestParam Long parishId,@RequestParam int pageNum, @RequestParam int pageSize)
    {
        return ResponseEntity.ok(userService.getAllUsersByParish(parishId,new PageRequest(pageNum,pageSize)));
    }
    @GetMapping("/status")
    public ResponseEntity<?> getAllByStatus(@RequestParam Status status, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        return ResponseEntity.ok(userService.getAllUsersByStatus(status,new PageRequest(pageNum,pageSize)));
    }
    @GetMapping("email")
    public ResponseEntity<?> getAllByEmailAddress(@RequestParam String emailAddress)
    {
        return ResponseEntity.ok(userService.findUserByEmailAddress(emailAddress));
    }
    @GetMapping("/phone")
    public ResponseEntity<?> getAllByPhone(@RequestParam String phoneNumber)
    {
        return ResponseEntity.ok(userService.findByPhoneNumber(phoneNumber));
    }
    @GetMapping("/count")
    public ResponseEntity<?> countByParish(@RequestParam Long parishId)
    {
        return ResponseEntity.ok(userService.countUserByParishId(parishId));
    }
}
