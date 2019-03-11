package nsk.cath.com.controller;


import lombok.extern.slf4j.Slf4j;
import nsk.cath.com.dto.ContactRequest;
import nsk.cath.com.dto.UserDetail;
import nsk.cath.com.enums.Constants;
import nsk.cath.com.errorHandler.ErrorDetails;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.service.contact.ContactService;
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
@RequestMapping("/contact")
@Consumes(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class ContactController {
    @Autowired
    ContactService contactService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ContactRequest contactRequest,
                                    @ApiIgnore @RequestAttribute(Constants.USER_DETAIL)UserDetail userDetail)
    {
        try {
            contactService.setUp(contactRequest,userDetail,false);
        } catch (NSKException e) {
            log.error("NSKEscption caught ",e);
            ErrorDetails.setUpErrors("User creation failed", Arrays.asList(e.getMessage()),e.getCode());
        }
        return ResponseEntity.status(200).body("");
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ContactRequest userRequest,
                                    @ApiIgnore @RequestAttribute(Constants.USER_DETAIL)UserDetail userDetail)
    {
        try {
            contactService.setUp(userRequest,userDetail,true);
        } catch (NSKException e) {
            log.error("NSKException caught ",e);
            ErrorDetails.setUpErrors("User creation failed", Arrays.asList(e.getMessage()),e.getCode());
        }
        return ResponseEntity.status(200).body("");
    }
    @GetMapping
    public ResponseEntity<?> findByUser(@RequestParam Long userId)
    {
        return ResponseEntity.ok(contactService.findByUser(userId));
    }
    @GetMapping("/city")
    public ResponseEntity<?> findByCity(@RequestParam String city,@RequestParam int pageNum,@RequestParam int pageSize)
    {
        return ResponseEntity.ok(contactService.findByCity(city,new PageRequest(pageNum,pageSize)));
    }
    @GetMapping("/address")
    public ResponseEntity<?> findByHomeAddress(@RequestParam boolean isHome,@RequestParam int pageNum,@RequestParam int pageSize)
    {
        return ResponseEntity.ok(contactService.findByHomeAddress(isHome,new PageRequest(pageNum,pageSize)));
    }
    @GetMapping("/house")
    public ResponseEntity<?> findByHouseAddress(@RequestParam String houseAddress,@RequestParam int pageNum,@RequestParam int pageSize)
    {
        return ResponseEntity.ok(contactService.findByHouseAddress(houseAddress,new PageRequest(pageNum,pageSize)));
    }
}
