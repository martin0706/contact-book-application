package com.ltp.contactbook.repository;

import com.ltp.contactbook.entity.Email;
import com.ltp.contactbook.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
