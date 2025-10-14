package com.YEP4510.YEP4510.Repositories;

import com.YEP4510.YEP4510.Models.Classificacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClassificacaoRepository extends CrudRepository<Classificacao, Long>, PagingAndSortingRepository<Classificacao, Long> {
}
