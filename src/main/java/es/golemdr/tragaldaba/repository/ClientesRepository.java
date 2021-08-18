package es.golemdr.tragaldaba.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long>{

}
