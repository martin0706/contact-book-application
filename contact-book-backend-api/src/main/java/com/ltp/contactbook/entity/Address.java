package com.ltp.contactbook.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name = "t_addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresss_seq")
    @SequenceGenerator(name = "addresss_seq", sequenceName = "addresss_sequence", allocationSize = 1, initialValue = 1000000000)
    private Long id;

    @Column(name = "addr_type")
    private String addressType;

    @Column(name = "addr_info")
    private String addressInfo;

    @ManyToOne
    @JoinColumn(name = "T_PEOPLE_ID", nullable = false)
    private Person person;

}
