package es.golemdr.tragaldaba.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.tragaldaba.domain.Plato;
import es.golemdr.tragaldaba.repository.PlatosRepository;

@Service
public class PlatosService {

		@Autowired
		private PlatosRepository platosRepository;


		public List<Plato> getPlatos() {

			return platosRepository.findAll();

		}


		public List<Plato> getPlatos(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return platosRepository.findAll(paginacion).getContent();

		}


		public int getTotalPlatos(){

			return platosRepository.findAll().size();

		}


		public Plato insertarActualizarPlato(Plato plato) {

			return platosRepository.save(plato);

		}


		public Plato getById(Long idPlato) {

			return platosRepository.findById(idPlato).get();

		}


		public void borrarPlato(Long idPlato) {

			platosRepository.deleteById(idPlato);

		}

}

