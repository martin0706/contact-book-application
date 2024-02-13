package com.ltp.contactbook.service;

import com.ltp.contactbook.dto.PersonDTO;
import com.ltp.contactbook.entity.Person;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PersonService {

    PersonDTO savePerson(PersonDTO personDTO);
    PersonDTO updatePerson(PersonDTO personDTO, Long personId);

    PersonDTO getPerson(Long personId);

    List<PersonDTO> getPersons();
    List<PersonDTO> findByFullNameContaining(String name);

    void deletePerson(Long personId);
}
