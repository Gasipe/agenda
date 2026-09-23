package no.group.estudos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.group.estudos.entities.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContactRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
