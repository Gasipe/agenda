package no.group.estudos.repository;

import no.group.estudos.dto.ContactResponseDTO;
import no.group.estudos.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    Optional<ContactResponseDTO> findByUserId(UUID id);

    List<Contact> findProjectedByUserId(UUID userId);
}
