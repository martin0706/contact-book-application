package com.ltp.contactbook.repository;

import com.ltp.contactbook.entity.Email;
import com.ltp.contactbook.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email,Long> {
    Email findByPersonId(Long personId);
}
