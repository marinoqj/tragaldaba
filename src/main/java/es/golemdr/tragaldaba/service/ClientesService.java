package es.golemdr.tragaldaba.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.tragaldaba.domain.Cliente;
import es.golemdr.tragaldaba.repository.ClientesRepository;

@Service
public class ClientesService {

		@Autowired
		private ClientesRepository clientesRepository;


		public List<Cliente> getClientes() {

			return clientesRepository.findAll();

		}


		public List<Cliente> getClientes(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return clientesRepository.findAll(paginacion).getContent();

		}


		public int getTotalClientes(){

			return clientesRepository.findAll().size();

		}


		public Cliente insertarActualizarCliente(Cliente cliente) {

			return clientesRepository.save(cliente);

		}


		public Cliente getById(Long idCliente) {
			
			Cliente resultado = null;
			
			Optional<Cliente> cliente = clientesRepository.findById(idCliente);
			
			if(cliente.isPresent()) {
				resultado = cliente.get();
			}

			return resultado;

		}


		public void borrarCliente(Long idCliente) {

			clientesRepository.deleteById(idCliente);

		}


		public Cliente existeCliente(String dni) {
			List<Cliente> listadoClientes = getClientes();
			Cliente resultado = null;
			
			for(Cliente cliente : listadoClientes) {
				if (cliente.getDni().equals(dni)) {
					resultado = cliente;
					break;
				}
			}
			
			return resultado;
		}

}

