package es.golemdr.tragaldaba.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.repository.ConstantesRepository;

@Service
public class ConstantesService {

		@Autowired
		private ConstantesRepository constantesRepository;


		public List<Constante> getConstantes() {

			return constantesRepository.findAll();

		}


		public List<Constante> getConstantes(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return constantesRepository.findAll(paginacion).getContent();

		}


		public int getTotalConstantes(){

			return constantesRepository.findAll().size();

		}


		public Constante insertarActualizarConstante(Constante constante) {

			return constantesRepository.save(constante);

		}


		public Constante getById(Long idConstante) {

			return constantesRepository.findById(idConstante).get();

		}


		public void borrarConstante(Long idConstante) {

			constantesRepository.deleteById(idConstante);

		}

}

