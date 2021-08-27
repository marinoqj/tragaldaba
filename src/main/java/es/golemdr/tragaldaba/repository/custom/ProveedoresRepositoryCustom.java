package es.golemdr.tragaldaba.repository.custom;


import java.util.List;

import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Proveedor;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;


@Repository
public interface ProveedoresRepositoryCustom{

	List<Proveedor> findProveedores(Proveedor proveedor, PaginacionBean paginacion);

}
