package com.YEP4510.YEP4510.Repositories;

import com.YEP4510.YEP4510.Models.Presidente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PresidenteRepository extends CrudRepository<Presidente, Long>, PagingAndSortingRepository<Presidente, Long> {
}
