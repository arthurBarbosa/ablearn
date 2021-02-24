package com.abcode.learn.resources;

import com.abcode.learn.dto.DeliverRevisionDTO;
import com.abcode.learn.services.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliveryResource {

    @Autowired
    private DeliverService service;



    @PutMapping(value = "/{id}")
    private ResponseEntity<Void> saveRevision(@PathVariable Long id, @RequestBody DeliverRevisionDTO dto) {
        service.saveRevision(id, dto);
        return ResponseEntity.noContent().build();
    }
}
