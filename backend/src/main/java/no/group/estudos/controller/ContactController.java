package no.group.estudos.controller;

import lombok.AllArgsConstructor;
import no.group.estudos.dto.ContactRequestDTO;
import no.group.estudos.dto.ContactResponseDTO;
import no.group.estudos.dto.ContactUpdate;
import no.group.estudos.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor

public class ContactController {
    private final ContactService service;

    @GetMapping
    public ResponseEntity <List<ContactResponseDTO>> findAll() {
        List<ContactResponseDTO> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity <ContactResponseDTO> findById(@PathVariable UUID id) {
        ContactResponseDTO response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <ContactResponseDTO> save(@RequestBody ContactRequestDTO contactRequestDTO) {
        ContactResponseDTO response = service.save(contactRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ContactResponseDTO> update(@PathVariable UUID id, @RequestBody ContactUpdate update) {
        ContactResponseDTO response = service.updateById(id, update);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
