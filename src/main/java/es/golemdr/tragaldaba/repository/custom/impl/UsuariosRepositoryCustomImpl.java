package es.golemdr.tragaldaba.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Usuario;
import es.golemdr.tragaldaba.repository.custom.UsuariosRepositoryCustom;




public class UsuariosRepositoryCustomImpl implements UsuariosRepositoryCustom{
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional
	public Usuario recuperarUsuarioLogin(String login) {
		
		Usuario usuarioResult = null;
		
		try {
			
			Session session = em.unwrap(Session.class);
			
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT LOGIN, PASSWORD, NOMBRE, APELLIDO1, APELLIDO2 FROM USUARIOS WHERE LOGIN = ?");
			
			NativeQuery query = session.createNativeQuery(queryString.toString());
			
			query.setParameter(1, login);
			
			Object[] usuario = (Object[]) query.getSingleResult();
			
			usuarioResult = new Usuario();
			
			usuarioResult.setLogin(usuario[0].toString());
			usuarioResult.setPassword(usuario[1].toString());
			usuarioResult.setNombre(usuario[2].toString());
			usuarioResult.setApellido1(usuario[3].toString());
			usuarioResult.setApellido2(usuario[4].toString());			
					
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return usuarioResult;
	};

}
