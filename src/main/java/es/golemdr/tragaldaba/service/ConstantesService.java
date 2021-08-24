package es.golemdr.tragaldaba.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.tragaldaba.domain.Cliente;
import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.repository.ConstantesRepository;

@Service
public class ConstantesService {

		@Autowired
		private ConstantesRepository constantesRepository;


		public List<Constante> getConstantes() {

			return constantesRepository.findAll();

		}


		public List<Constante> getConstantes(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());
			
			paginacionBean.setTotalRegistros(getTotalConstantes());

			return constantesRepository.findAll(paginacion).getContent();

		}		


		public int getTotalConstantes(){

			return constantesRepository.findAll().size();

		}


		public Constante insertarActualizarConstante(Constante constante) {

			return constantesRepository.save(constante);

		}


		public Constante getById(Long idConstante) {

			Constante resultado = null;
			
			Optional<Constante> constante = constantesRepository.findById(idConstante);
			
			if(constante.isPresent()) {
				resultado = constante.get();
			}

			return resultado;

		}


		public void borrarConstante(Long idConstante) {

			constantesRepository.deleteById(idConstante);

		}
		
		public List<Constante> findConstantesByExample(Constante constante, PaginacionBean paginacion) {

			return constantesRepository.findConstantes(constante, paginacion);

		}
		
		public int countConstantesByExample(Constante constante) {

			return constantesRepository.findConstantes(constante, null).size();

		}

}

