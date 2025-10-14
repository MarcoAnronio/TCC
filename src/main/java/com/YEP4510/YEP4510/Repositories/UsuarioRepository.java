package com.YEP4510.YEP4510.Repositories;

import com.YEP4510.YEP4510.Models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>, PagingAndSortingRepository<Usuario, Long> {
}
