package es.golemdr.tragaldaba.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import es.golemdr.tragaldaba.domain.Usuario;
import es.golemdr.tragaldaba.repository.custom.UsuariosRepositoryCustom;





public interface UsuariosRepository extends JpaRepository<Usuario, Long>, UsuariosRepositoryCustom{

}
