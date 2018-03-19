package com.cat.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cat.domain.Kitty;

public interface KittyRepository extends PagingAndSortingRepository<Kitty, Long> {
	Page<Kitty> findAllByCustomerIsNull(Pageable pageRequest);
	
	Page<Kitty> findAllByIsForSaleIsTrueOrderByCustomerDescIdDesc(Pageable pageRequest);
	List<Kitty> findAllByIsForSaleIsTrueOrderByCustomerDescIdDesc();
}
