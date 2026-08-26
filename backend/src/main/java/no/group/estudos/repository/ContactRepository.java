package no.group.estudos.repository;

import no.group.estudos.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
