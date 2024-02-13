package com.ltp.contactbook.service;

import com.ltp.contactbook.dto.PersonDTO;
import com.ltp.contactbook.entity.Address;
import com.ltp.contactbook.entity.Email;
import com.ltp.contactbook.entity.Person;
import com.ltp.contactbook.exception.AddressNotFoundException;
import com.ltp.contactbook.exception.EmailNotFoundException;
import com.ltp.contactbook.exception.PersonNotFoundException;
import com.ltp.contactbook.repository.AddressRepository;
import com.ltp.contactbook.repository.EmailRepository;
import com.ltp.contactbook.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    EmailRepository emailRepository;

    AddressRepository addressRepository;


    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setFullName(personDTO.getFullName());
        person.setPin(personDTO.getPin());
        personRepository.save(person);

        Email email = new Email();
        email.setPerson(person);
        email.setEmailType(personDTO.getEmailType());
        email.setEmail(personDTO.getEmail());
        emailRepository.save(email);

        Address address = new Address();
        address.setPerson(person);
        address.setAddressType(personDTO.getAddressType());
        address.setAddressInfo(personDTO.getAddressInfo());
        addressRepository.save(address);
        return convertToDto(person, email, address);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO, Long personId) {
        Optional<Person> person = personRepository.findById(personId);

        if (person.isPresent()) {
            Person unwrappePerson = person.get();
            unwrappePerson.setFullName(personDTO.getFullName());
            unwrappePerson.setPin(personDTO.getPin());
            personRepository.save(unwrappePerson);


            Email email = emailRepository.findByPersonId(personId);
            if(email != null) {
                email.setPerson(unwrappePerson);
                email.setEmailType(personDTO.getEmailType());
                email.setEmail(personDTO.getEmail());
                emailRepository.save(email);
            }else{
                throw new EmailNotFoundException(personId);
            }

            Address address = addressRepository.findByPersonId(personId);
            if(address != null) {
                address.setPerson(unwrappePerson);
                address.setAddressType(personDTO.getAddressType());
                address.setAddressInfo(personDTO.getAddressInfo());
                addressRepository.save(address);
            }else{
                throw new AddressNotFoundException(personId);
            }

            return convertToDto(unwrappePerson, email, address);

        } else {
            throw new PersonNotFoundException(personId);
        }
    }

    @Override
    public PersonDTO getPerson(Long personId) {
        Optional<Person> person = personRepository.findById(personId);

        if (person.isPresent()) {
            Person unwrappePerson = person.get();
            Email email = emailRepository.findByPersonId(personId);
            Address address = addressRepository.findByPersonId(personId);

            return convertToDto(unwrappePerson, email, address);

        } else {
            throw new PersonNotFoundException(personId);
        }
    }

    @Override
    public List<PersonDTO> getPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();
        persons.forEach(person -> {
            Email email = emailRepository.findByPersonId(person.getId());
            Address address = addressRepository.findByPersonId(person.getId());
            personDTOList.add(convertToDto(person,email,address));
        });
        return personDTOList;
    }

    @Override
    public List<PersonDTO> findByFullNameContaining(String name) {
        List<Person> persons = personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();
        persons.forEach(person -> {
            if (person.getFullName().toLowerCase().contains(name.toLowerCase())) {
                Email email = emailRepository.findByPersonId(person.getId());
                Address address = addressRepository.findByPersonId(person.getId());
                personDTOList.add(convertToDto(person,email,address));
            }
        });
        return personDTOList;
    }

    @Override
    public void deletePerson(Long personId) {
        Email email = emailRepository.findByPersonId(personId);
        Address address = addressRepository.findByPersonId(personId);

        if(email != null) {
        emailRepository.deleteById(email.getId());
        }else{
            throw new EmailNotFoundException(personId);
        }
        if(address != null) {
            addressRepository.deleteById(address.getId());
        }else{
            throw new AddressNotFoundException(personId);
        }
        personRepository.deleteById(personId);

    }

    private PersonDTO convertToDto(Person person, Email email, Address address) {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFullName(person.getFullName());
        personDTO.setPin(person.getPin());
        if(email != null) {
            personDTO.setEmailType(email.getEmailType());
            personDTO.setEmail(email.getEmail());
        }else{
            throw new EmailNotFoundException(person.getId());
        }
        if(address != null) {
            personDTO.setAddressType(address.getAddressType());
            personDTO.setAddressInfo(address.getAddressInfo());
        }else{
            throw new AddressNotFoundException(person.getId());
        }

        return personDTO;
    }
}
