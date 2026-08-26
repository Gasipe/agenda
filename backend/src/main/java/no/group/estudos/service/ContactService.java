package no.group.estudos.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import no.group.estudos.dto.ContactRequestDTO;
import no.group.estudos.dto.ContactResponseDTO;
import no.group.estudos.dto.ContactUpdate;
import no.group.estudos.entities.Contact;
import no.group.estudos.mapper.ContactMapper;
import no.group.estudos.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor

public class ContactService {
    private final ContactRepository repository;

    public ContactResponseDTO save(ContactRequestDTO dto) {
        Contact contact = ContactMapper.toEntity(dto);
        contact = repository.save(contact);
        return ContactMapper.toDTO(contact);
    }

    public ContactResponseDTO findById(UUID id) {
        Contact contact = repository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        return ContactMapper.toDTO(contact);
    }

    public List<ContactResponseDTO> findAll() {
        List<Contact> contacts = repository.findAll();
        return contacts.stream().map(ContactMapper::toDTO).toList();
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public ContactResponseDTO updateById(UUID id, ContactUpdate update) {
        Contact contact = repository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contact.setFirstName(update.getFirstName());
        contact.setLastName(update.getLastName());
        contact.setEmail(update.getEmail());
        contact.setPhone(update.getPhone());
        return ContactMapper.toDTO(repository.save(contact));
    }
}
