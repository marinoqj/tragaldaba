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
import es.golemdr.tragaldaba.domain.Plato;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.service.PlatosService;


@Controller
public class PlatosController {

	private static Logger log = LogManager.getLogger(PlatosController.class);

	private static final String PLATOS = "platos";
	private static final String PLATO = "plato";

	@Autowired
	private PlatosService platosService;



	@GetMapping(value=UrlConstants.URL_LISTADO_PLATOS)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Plato> resultado = null;
		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(Integer.valueOf(inicio - 1));

		resultado = platosService.getPlatos(paginacion.getInicio(), paginacion.getElementosXpagina());

		map.put("paginacion", paginacion);
		map.put(PLATOS, resultado);
		map.put(PLATO,new Plato());

		return ForwardConstants.FWD_LISTADO_PLATOS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_PLATO)
	public String insertar(Plato plato, Model model) {


		platosService.insertarActualizarPlato(plato);

		return ForwardConstants.RED_LISTADO_PLATOS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_PLATO)
	public String editar(String idPlato, Map<String, Object> map) {

		Plato resultado = null;

		resultado = platosService.getById(new Long(idPlato));

		map.put("modo", "actualizar");
		map.put(PLATO,resultado);

		return ForwardConstants.FWD_PLATO_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_PLATO)
	public String actualizar(@Valid Plato plato, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_PLATO_FORM;

		}else{

			platosService.insertarActualizarPlato(plato);
			destino = ForwardConstants.RED_LISTADO_PLATOS;

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_PLATO)
	public String borrar(String idPlato, Map<String, Object> map) {

		platosService.borrarPlato(new Long(idPlato));

		return ForwardConstants.RED_LISTADO_PLATOS;

	}



}

