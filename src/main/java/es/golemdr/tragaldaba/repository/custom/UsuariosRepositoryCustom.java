package es.golemdr.tragaldaba.repository.custom;

import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Usuario;




@Repository
public interface UsuariosRepositoryCustom {
	
	public Usuario recuperarUsuarioLogin(String login);

}
