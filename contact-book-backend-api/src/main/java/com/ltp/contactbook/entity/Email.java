package com.ltp.contactbook.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name = "t_mail")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_seq")
    @SequenceGenerator(name = "email_seq", sequenceName = "email_sequence", allocationSize = 1, initialValue = 1000000000)
    private Long id;

    @Column(name = "email_type")
    private String emailType;

    @Column(name = "email")
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "T_PEOPLE_ID", nullable = false)
    private Person person;

}
