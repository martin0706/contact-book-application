package com.ltp.contactbook.web;

import com.ltp.contactbook.dto.PersonDTO;
import com.ltp.contactbook.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/person")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    PersonService personService;
    @PostMapping("/create")
    public ResponseEntity<?> savePerson(@Valid @RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.savePerson(personDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{personId}")
    public ResponseEntity<?> updatePerson(@Valid @RequestBody PersonDTO personDTO,@PathVariable Long personId) {
        return new ResponseEntity<>(personService.updatePerson(personDTO,personId), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getPersons() {
        return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<?> getPerson(@PathVariable Long personId) {
        return new ResponseEntity<>(personService.getPerson(personId), HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> searchName(@RequestParam String contains) {
        return new ResponseEntity<>(personService.findByFullNameContaining(contains), HttpStatus.OK);
    }

    @DeleteMapping("/delete/id={personId}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
