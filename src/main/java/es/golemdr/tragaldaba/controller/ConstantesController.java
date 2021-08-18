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
import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.service.ConstantesService;


@Controller
public class ConstantesController {

	private static Logger log = LogManager.getLogger(ConstantesController.class);

	private static final String CONSTANTES = "constantes";
	private static final String CONSTANTE = "constante";

	@Autowired
	private ConstantesService constantesService;



	@GetMapping(value=UrlConstants.URL_LISTADO_CONSTANTES)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Constante> resultado = null;
		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(Integer.valueOf(inicio - 1));

		resultado = constantesService.getConstantes(paginacion.getInicio(), paginacion.getElementosXpagina());

		map.put("paginacion", paginacion);
		map.put(CONSTANTES, resultado);
		map.put(CONSTANTE,new Constante());

		return ForwardConstants.FWD_LISTADO_CONSTANTES;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_CONSTANTE)
	public String insertar(Constante constante, Model model) {


		constantesService.insertarActualizarConstante(constante);

		return ForwardConstants.RED_LISTADO_CONSTANTES; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_CONSTANTE)
	public String editar(String idConstante, Map<String, Object> map) {

		Constante resultado = null;

		resultado = constantesService.getById(new Long(idConstante));

		map.put("modo", "actualizar");
		map.put(CONSTANTE,resultado);

		return ForwardConstants.FWD_CONSTANTE_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_CONSTANTE)
	public String actualizar(@Valid Constante constante, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_CONSTANTE_FORM;

		}else{

			constantesService.insertarActualizarConstante(constante);
			destino = ForwardConstants.RED_LISTADO_CONSTANTES;

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_CONSTANTE)
	public String borrar(String idConstante, Map<String, Object> map) {

		constantesService.borrarConstante(new Long(idConstante));

		return ForwardConstants.RED_LISTADO_CONSTANTES;

	}



}

