package es.golemdr.tragaldaba.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.golemdr.tragaldaba.controller.constantes.ForwardConstants;
import es.golemdr.tragaldaba.controller.constantes.UrlConstants;
import es.golemdr.tragaldaba.domain.Pedido;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.service.PedidosService;


@Controller
public class PedidosController {

	private static Logger log = LogManager.getLogger(PedidosController.class);

	private static final String PEDIDOS = "pedidos";
	private static final String PEDIDO = "pedido";

	@Autowired
	private PedidosService pedidosService;



	@GetMapping(value=UrlConstants.URL_LISTADO_PEDIDOS)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Pedido> resultado = null;
		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = pedidosService.getPedidos(paginacion.getInicio(), paginacion.getElementosXpagina());

		map.put("paginacion", paginacion);
		map.put(PEDIDOS, resultado);
		map.put(PEDIDO,new Pedido());

		return ForwardConstants.FWD_LISTADO_PEDIDOS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_PEDIDO)
	public String insertar(Pedido pedido, Model model) {


		pedidosService.insertarActualizarPedido(pedido);

		return ForwardConstants.RED_LISTADO_PEDIDOS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_PEDIDO)
	public String editar(String idPedido, Map<String, Object> map) {

		Pedido resultado = null;

		resultado = pedidosService.getById(Long.valueOf(idPedido));

		map.put("modo", "actualizar");
		map.put(PEDIDO,resultado);

		return ForwardConstants.FWD_PEDIDO_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_PEDIDO)
	public String actualizar(@Valid Pedido pedido, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_PEDIDO_FORM;

		}else{

			pedidosService.insertarActualizarPedido(pedido);
			destino = ForwardConstants.RED_LISTADO_PEDIDOS;

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_PEDIDO)
	public String borrar(String idPedido, Map<String, Object> map) {

		pedidosService.borrarPedido(Long.valueOf(idPedido));

		return ForwardConstants.RED_LISTADO_PEDIDOS;

	}



}

