package no.group.estudos.controller;

import lombok.AllArgsConstructor;
import no.group.estudos.dto.ContactRequestDTO;
import no.group.estudos.dto.ContactResponseDTO;
import no.group.estudos.dto.ContactUpdate;
import no.group.estudos.entities.Contact;
import no.group.estudos.entities.User;
import no.group.estudos.repository.ContactRepository;
import no.group.estudos.security.userdetails.UserDetailsImpl;
import no.group.estudos.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor

public class ContactController {
    private final ContactService service;

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> findAll(Authentication authenticaton) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticaton.getPrincipal();
        List<ContactResponseDTO> contacts = service.findAllByUser(userDetails.getUser().getId());
        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(@RequestBody ContactRequestDTO requestDTO, Authentication authenticaton) {
        UserDetailsImpl userDetailsImpl = ((UserDetailsImpl) authenticaton.getPrincipal());
        ContactResponseDTO response = service.save(requestDTO, userDetailsImpl.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
