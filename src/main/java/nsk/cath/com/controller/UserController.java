package nsk.cath.com.controller;

import nsk.cath.com.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Consumes(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserController {

    @POST
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest)
    {
        return ResponseEntity.status(200).body("");
    }
}
