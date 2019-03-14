package nsk.cath.com.controller;

import lombok.extern.slf4j.Slf4j;
import nsk.cath.com.dto.auth.RoleRequest;
import nsk.cath.com.errorHandler.ErrorDetails;
import nsk.cath.com.model.auth.Role;
import nsk.cath.com.service.auth.PrivilegeService;
import nsk.cath.com.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    private RoleService roleService;
    private PrivilegeService privilegeService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @PostMapping
    public ResponseEntity createUserRole(@Valid @RequestBody RoleRequest role) {
        return roleService.setUp(role);
    }

}
