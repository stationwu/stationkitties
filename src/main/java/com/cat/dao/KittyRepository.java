package com.cat.dao;


import org.springframework.data.repository.CrudRepository;

import com.cat.domain.Kitty;

public interface KittyRepository extends CrudRepository<Kitty, Long> {
}
