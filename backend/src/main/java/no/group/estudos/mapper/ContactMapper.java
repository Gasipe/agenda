package no.group.estudos.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import no.group.estudos.dto.ContactRequestDTO;
import no.group.estudos.dto.ContactResponseDTO;
import no.group.estudos.dto.ContactUpdate;
import no.group.estudos.entities.Contact;

@AllArgsConstructor

public class ContactMapper {

    public static ContactResponseDTO toDTO(Contact contact) {
        return new ContactResponseDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone()
        );
    }

    public static Contact toEntity(ContactRequestDTO request) {
        return Contact.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    public static void updateEntity(ContactUpdate update, Contact contact) {
        contact.setFirstName(update.getFirstName());
        contact.setLastName(update.getLastName());
        contact.setEmail(update.getEmail());
        contact.setPhone(update.getPhone());
    }
}
