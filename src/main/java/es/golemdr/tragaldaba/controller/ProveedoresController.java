package es.golemdr.tragaldaba.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.golemdr.tragaldaba.controller.constantes.ForwardConstants;
import es.golemdr.tragaldaba.controller.constantes.UrlConstants;
import es.golemdr.tragaldaba.domain.Proveedor;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.service.ProveedoresService;


@Controller
public class ProveedoresController {

	private static Logger log = LogManager.getLogger(ProveedoresController.class);

	private static final String PROVEEDORES = "proveedores";
	private static final String PROVEEDOR = "proveedor";

	@Autowired
	private ProveedoresService proveedoresService;



	@GetMapping(value=UrlConstants.URL_LISTADO_PROVEEDORES)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Proveedor> resultado = null;
		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(Integer.valueOf(inicio - 1));

		resultado = proveedoresService.getProveedores(paginacion.getInicio(), paginacion.getElementosXpagina());

		map.put("paginacion", paginacion);
		map.put(PROVEEDORES, resultado);
		map.put(PROVEEDOR,new Proveedor());

		return ForwardConstants.FWD_LISTADO_PROVEEDORES;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_PROVEEDOR)
	public String insertar(Proveedor proveedor, Model model) {


		proveedoresService.insertarActualizarProveedor(proveedor);

		return ForwardConstants.RED_LISTADO_PROVEEDORES; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_PROVEEDOR)
	public String editar(String idProveedor, Map<String, Object> map) {

		Proveedor resultado = null;

		resultado = proveedoresService.getById(new Long(idProveedor));

		map.put("modo", "actualizar");
		map.put(PROVEEDOR,resultado);

		return ForwardConstants.FWD_PROVEEDOR_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_PROVEEDOR)
	public String actualizar(@Valid Proveedor proveedor, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_PROVEEDOR_FORM;

		}else{

			proveedoresService.insertarActualizarProveedor(proveedor);
			destino = ForwardConstants.RED_LISTADO_PROVEEDORES;

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_PROVEEDOR)
	public String borrar(String idProveedor, Map<String, Object> map) {

		proveedoresService.borrarProveedor(new Long(idProveedor));

		return ForwardConstants.RED_LISTADO_PROVEEDORES;

	}



}

