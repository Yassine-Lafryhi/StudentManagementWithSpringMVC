package com.student.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

enum Gender {
    MASCULINE,
    FEMININE
}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 50)
    private String firstName;

    @NotEmpty
    @Size(min = 4, max = 50)
    private String lastName;

    @NotEmpty
    @Size(max = 50)
    private String email;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    @Past
    private Date birthDate;

    private Gender gender;

    private boolean enRegle;
}
