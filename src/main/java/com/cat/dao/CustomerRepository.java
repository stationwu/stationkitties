package com.cat.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cat.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findOneByOpenCode(String openCode);

    @Query("select count(c)>0 from Customer c where c.openCode = ?1")
    boolean isCustomerAlreadyRegistered(String openCode);
}
