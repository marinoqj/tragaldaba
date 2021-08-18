package es.golemdr.tragaldaba.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.tragaldaba.domain.Pedido;
import es.golemdr.tragaldaba.repository.PedidosRepository;

@Service
public class PedidosService {

		@Autowired
		private PedidosRepository pedidosRepository;


		public List<Pedido> getPedidos() {

			return pedidosRepository.findAll();

		}


		public List<Pedido> getPedidos(int inicio, int elementosXpagina) {

			Pageable paginacion = PageRequest.of(inicio,elementosXpagina);

			return pedidosRepository.findAll(paginacion).getContent();

		}


		public int getTotalPedidos(){

			return pedidosRepository.findAll().size();

		}


		public Pedido insertarActualizarPedido(Pedido pedido) {

			return pedidosRepository.save(pedido);

		}


		public Pedido getById(Long idPedido) {

			return pedidosRepository.findById(idPedido).get();

		}


		public void borrarPedido(Long idPedido) {

			pedidosRepository.deleteById(idPedido);

		}

}

