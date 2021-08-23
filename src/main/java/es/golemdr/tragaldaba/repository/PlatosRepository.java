package es.golemdr.tragaldaba.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Plato;
import es.golemdr.tragaldaba.repository.custom.PlatosRepositoryCustom;

@Repository
public interface PlatosRepository extends JpaRepository<Plato, Long>, PlatosRepositoryCustom{

}
