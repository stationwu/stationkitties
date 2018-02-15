package com.cat.dao;


import org.springframework.data.repository.CrudRepository;

import com.cat.domain.Gene;

public interface GeneRepository extends CrudRepository<Gene, Long> {
}
