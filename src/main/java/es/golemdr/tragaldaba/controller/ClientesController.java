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
import es.golemdr.tragaldaba.domain.Cliente;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.service.ClientesService;


@Controller
public class ClientesController {

	private static Logger log = LogManager.getLogger(ClientesController.class);

	private static final String CLIENTES = "clientes";
	private static final String CLIENTE = "cliente";

	@Autowired
	private ClientesService clientesService;



	@GetMapping(value=UrlConstants.URL_LISTADO_CLIENTES)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Cliente> resultado = null;
		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = clientesService.getClientes(paginacion.getInicio(), paginacion.getElementosXpagina());

		map.put("paginacion", paginacion);
		map.put(CLIENTES, resultado);
		map.put(CLIENTE,new Cliente());

		return ForwardConstants.FWD_LISTADO_CLIENTES;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_CLIENTE)
	public String insertar(Cliente cliente, Model model) {


		clientesService.insertarActualizarCliente(cliente);

		return ForwardConstants.RED_LISTADO_CLIENTES; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_CLIENTE)
	public String editar(String idCliente, Map<String, Object> map) {

		Cliente resultado = null;
		resultado = clientesService.getById(Long.valueOf(idCliente));

		map.put("modo", "actualizar");
		map.put(CLIENTE,resultado);

		return ForwardConstants.FWD_CLIENTE_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_CLIENTE)
	public String actualizar(@Valid Cliente cliente, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_CLIENTE_FORM;

		}else{

			clientesService.insertarActualizarCliente(cliente);
			destino = ForwardConstants.RED_LISTADO_CLIENTES;

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_CLIENTE)
	public String borrar(String idCliente, Map<String, Object> map) {

		clientesService.borrarCliente(new Long(idCliente));

		return ForwardConstants.RED_LISTADO_CLIENTES;

	}
	

}

