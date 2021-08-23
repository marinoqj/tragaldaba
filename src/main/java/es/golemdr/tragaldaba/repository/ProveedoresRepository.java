package es.golemdr.tragaldaba.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Proveedor;
import es.golemdr.tragaldaba.repository.custom.ProveedoresRepositoryCustom;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedor, Long>, ProveedoresRepositoryCustom{

}
