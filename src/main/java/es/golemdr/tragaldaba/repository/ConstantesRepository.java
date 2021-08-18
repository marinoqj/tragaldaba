package es.golemdr.tragaldaba.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Constante;

@Repository
public interface ConstantesRepository extends JpaRepository<Constante, Long>{

}
