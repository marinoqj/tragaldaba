package es.golemdr.tragaldaba.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.tragaldaba.domain.Usuario;
import es.golemdr.tragaldaba.repository.UsuariosRepository;

@Service
public class UsuariosService {

		@Autowired
		private UsuariosRepository usuariosRepository;


		public List<Usuario> getUsuarios() {

			return usuariosRepository.findAll();

		}


		public List<Usuario> getUsuarios(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return usuariosRepository.findAll(paginacion).getContent();

		}


		public int getTotalUsuarios(){

			return usuariosRepository.findAll().size();

		}


		public Usuario insertarActualizarUsuario(Usuario usuario) {

			return usuariosRepository.save(usuario);

		}


		public Usuario getById(Long idUsuario) {

			return usuariosRepository.findById(idUsuario).get();

		}


		public void borrarUsuario(Long idUsuario) {

			usuariosRepository.deleteById(idUsuario);

		}
		
		public Usuario getByExample(Usuario usuario) {
			
			Example<Usuario> example = Example.of(usuario);

			return usuariosRepository.findOne(example).get();

		}

}

