package es.golemdr.tragaldaba.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Plato;

@Repository
public interface PlatosRepository extends JpaRepository<Plato, Long>{

}
