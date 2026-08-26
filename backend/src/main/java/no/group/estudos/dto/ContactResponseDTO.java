package no.group.estudos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContactResponseDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
