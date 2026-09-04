package no.group.estudos.repository;

import no.group.estudos.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findByUserId(UUID id);
}
