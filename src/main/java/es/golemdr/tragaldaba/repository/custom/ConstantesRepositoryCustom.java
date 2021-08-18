package es.golemdr.tragaldaba.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;




@Repository
public interface ConstantesRepositoryCustom {
	
	List<Constante> findConstantes(Constante constante, PaginacionBean paginacion);

}
