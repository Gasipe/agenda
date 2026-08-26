package no.group.estudos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContactUpdate {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate updatedDate;
}
