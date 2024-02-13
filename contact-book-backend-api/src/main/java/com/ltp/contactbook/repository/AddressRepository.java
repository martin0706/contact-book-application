package com.ltp.contactbook.repository;

import com.ltp.contactbook.entity.Address;
import com.ltp.contactbook.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findByPersonId(Long personId);
}
