package nsk.cath.com.controller;

import lombok.extern.slf4j.Slf4j;
import nsk.cath.com.dto.UserRequest;
import nsk.cath.com.errorHandler.ErrorDetails;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    @POST
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
}
