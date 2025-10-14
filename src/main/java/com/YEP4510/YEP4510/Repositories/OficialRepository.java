package com.YEP4510.YEP4510.Repositories;

import com.YEP4510.YEP4510.Models.Oficial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OficialRepository extends CrudRepository<Oficial, Long>, PagingAndSortingRepository<Oficial, Long> {
}
