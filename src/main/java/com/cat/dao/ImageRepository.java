package com.cat.dao;


import org.springframework.data.repository.CrudRepository;

import com.cat.domain.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
