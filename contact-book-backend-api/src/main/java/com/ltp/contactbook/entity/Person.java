package com.ltp.contactbook.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name = "t_people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "people_seq")
    @SequenceGenerator(name = "people_seq", sequenceName = "people_sequence", allocationSize = 1, initialValue = 1000000000)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column( name = "pin")
    private String pin;

}
