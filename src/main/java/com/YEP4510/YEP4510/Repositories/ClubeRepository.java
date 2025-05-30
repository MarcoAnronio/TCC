package com.YEP4510.YEP4510.Repositories;

import com.YEP4510.YEP4510.Models.Clube;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClubeRepository extends CrudRepository<Clube, Integer>, PagingAndSortingRepository<Clube, Integer> {
}
