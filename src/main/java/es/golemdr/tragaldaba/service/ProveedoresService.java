package es.golemdr.tragaldaba.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.tragaldaba.domain.Proveedor;
import es.golemdr.tragaldaba.repository.ProveedoresRepository;

@Service
public class ProveedoresService {

		@Autowired
		private ProveedoresRepository proveedoresRepository;


		public List<Proveedor> getProveedores() {

			return proveedoresRepository.findAll();

		}


		public List<Proveedor> getProveedores(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return proveedoresRepository.findAll(paginacion).getContent();

		}


		public int getTotalProveedores(){

			return proveedoresRepository.findAll().size();

		}


		public Proveedor insertarActualizarProveedor(Proveedor proveedor) {

			return proveedoresRepository.save(proveedor);

		}


		public Proveedor getById(Long idProveedor) {

			return proveedoresRepository.findById(idProveedor).get();

		}


		public void borrarProveedor(Long idProveedor) {

			proveedoresRepository.deleteById(idProveedor);

		}

}

