package es.golemdr.tragaldaba.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.golemdr.tragaldaba.domain.PlatoMongo;
import es.golemdr.tragaldaba.repository.PlatosMongoRepository;

@Service
public class PlatosMongoService {

		@Autowired
		private PlatosMongoRepository platosMongoRepository;


		public List<PlatoMongo> getPlatos() {

			return platosMongoRepository.findAll();

		}


//		public List<PlatoMongo> getConstantes(PaginacionBean paginacionBean) {
//
//			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());
//			
//			paginacionBean.setTotalRegistros(getTotalPlatos());
//
//			return platosMongoRepository.findAll(paginacion).getContent();
//
//		}	


//		public int getTotalPlatos(){
//
//			return platosMongoRepository.findAll().size();
//
//		}


//		public PlatoMongo insertarActualizarPlato(PlatoMongo plato) {
//
//			return platosMongoRepository.save(plato);
//
//		}


		public PlatoMongo getById(Long idPlato) {

			return platosMongoRepository.findByIdPlato(idPlato);

		}


//		public void borrarPlato(Long idPlato) {
//
//			platosMongoRepository.deleteById(idPlato);
//
//		}

		
//		public List<PlatoMongo> findPlatosByExample(PlatoMongo plato, PaginacionBean paginacionBean) {
//			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());
//
//			return platosMongoRepository.findAll
//		}
		
//		public int countPlatosByExample(PlatoMongo plato) {
//
//			return platosMongoRepository.findPlatos(plato, null).size();
//
//		}		
		
		
		
//		public List<Plato> findPlatosByExample(Plato plato, PaginacionBean paginacion) {
//
//			return platosRepository.findPlatos(plato, paginacion);
//
//		}
//		
//		public int countPlatosByExample(Plato plato) {
//
//			return platosRepository.findPlatos(plato, null).size();
//
//		}		
		
}

