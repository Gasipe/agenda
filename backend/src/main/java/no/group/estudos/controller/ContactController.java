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
    private final ContactRepository respository;

    @GetMapping
    public List<Contact> findAll(Authentication authenticaton) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticaton.getPrincipal();
        UUID userId = userDetails.getUser().getId();
        return respository.findByUserId(userId);
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact, Authentication authenticaton) {
        User user = ((UserDetailsImpl) authenticaton.getPrincipal()).getUser();
        contact.setUser(user);
        return respository.save(contact);
    }
}
