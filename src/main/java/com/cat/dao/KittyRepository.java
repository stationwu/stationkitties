package com.cat.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cat.domain.Kitty;

public interface KittyRepository extends CrudRepository<Kitty, Long> {
    List<Kitty> findAllByCustomerIsNull();
}
