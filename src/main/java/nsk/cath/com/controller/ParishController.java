package nsk.cath.com.controller;

import lombok.extern.slf4j.Slf4j;
import nsk.cath.com.service.contact.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
@RequestMapping("/parish")
@Consumes(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class ParishController {
    @Autowired
    private ParishService parishService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam int pageNum,@RequestParam int pageSize)
    {
        return ResponseEntity.ok(parishService.findAll(new PageRequest(pageNum,pageSize)));
    }
    @GetMapping("/deanery")
    public ResponseEntity<?> findByDeanery(@RequestParam Long deaneryId)
    {
        return ResponseEntity.ok(parishService.findAllByDeanery(deaneryId));
    }
    @GetMapping("/id")
    public ResponseEntity<?>find(@RequestParam Long id)
    {
        return ResponseEntity.ok(parishService.findById(id));
    }
}
