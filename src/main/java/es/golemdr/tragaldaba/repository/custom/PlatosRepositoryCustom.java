package es.golemdr.tragaldaba.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Plato;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;




@Repository
public interface PlatosRepositoryCustom {
	
	List<Plato> findPlatos(Plato plato, PaginacionBean paginacion);

}
