package no.group.estudos.service;


import lombok.AllArgsConstructor;
import no.group.estudos.dto.ContactRequestDTO;
import no.group.estudos.dto.ContactResponseDTO;
import no.group.estudos.dto.ContactUpdate;
import no.group.estudos.entities.Contact;
import no.group.estudos.entities.User;
import no.group.estudos.mapper.ContactMapper;
import no.group.estudos.repository.ContactRepository;
import no.group.estudos.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor

public class ContactService {
    private final ContactRepository contactRepository;

    private final UserRepository userRepository;

        public ContactResponseDTO save(ContactRequestDTO dto, UUID userId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Contact contact = ContactMapper.toEntity(dto);
            contact.setUser(user);
            contact = contactRepository.save(contact);
            return ContactMapper.toDTO(contact);
        }


    public List<ContactResponseDTO> findAllByUser(UUID userId) {
        List<Contact> contacts = contactRepository.findProjectedByUserId(userId);

        if (contacts.isEmpty() && !userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        return contacts.stream().map(ContactMapper::toDTO).toList();
    }


        public ContactResponseDTO update(UUID userId, ContactUpdate update, UUID contactId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Contact contact = contactRepository.findById(contactId)
                    .orElseThrow(() -> new RuntimeException("Contact not found"));

            if (!contact.getUser().getId().equals(userId)) {
                throw new RuntimeException("Unauthorized: This contact does not belong to this user");
            }

            contact.setFirstName(update.getFirstName());
            contact.setLastName(update.getLastName());
            contact.setEmail(update.getEmail());
            contact.setPhone(update.getPhone());

            return ContactMapper.toDTO(contactRepository.save(contact));
        }


        public void delete(UUID userId, UUID contactId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Contact contact = contactRepository.findById(contactId)
                    .orElseThrow(() -> new RuntimeException("Contact not found"));

            if (!contact.getUser().getId().equals(userId)) {
                throw new RuntimeException("Unauthorized: This contact does not belong to this user");
            }

            contactRepository.delete(contact);
        }


}
