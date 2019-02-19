package nsk.cath.com.controller;

import nsk.cath.com.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/user")
public class UserController {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest)
    {
        return ResponseEntity.status(200).body("");
    }
}
