package es.golemdr.tragaldaba.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Usuario;
import es.golemdr.tragaldaba.repository.custom.UsuariosRepositoryCustom;




@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long>, UsuariosRepositoryCustom{

}
