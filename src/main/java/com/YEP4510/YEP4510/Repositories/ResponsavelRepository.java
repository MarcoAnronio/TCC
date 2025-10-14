package com.YEP4510.YEP4510.Repositories;

import com.YEP4510.YEP4510.Models.Responsavel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResponsavelRepository extends CrudRepository<Responsavel, Long>, PagingAndSortingRepository<Responsavel, Long> {
}
