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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import es.golemdr.tragaldaba.controller.constantes.ForwardConstants;
import es.golemdr.tragaldaba.controller.constantes.UrlConstants;
import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.ext.Constantes;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionFactory;
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
		boolean hayFiltro = false;
		
		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = constantesService.getConstantes(paginacion);

		map.put("paginacion", paginacion);
		map.put(CONSTANTES, resultado);
		map.put(CONSTANTE,new Constante());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

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
	
	@PostMapping(UrlConstants.URL_BUSCAR_CONSTANTES)
	public String buscarConstantes(Constante constante, Map<String, Object> map, HttpServletRequest request) {
		
		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;
		
		int total = 0;
		
		paginacion.setInicio(0);
		
		List<Constante> resultado = null;
		resultado = constantesService.findConstantesByExample(constante, paginacion);
		total = constantesService.countConstantesByExample(constante);

		
		if(total > paginacion.getElementosXpagina()){
			
			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, constante);
			

		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);
		
		map.put(CONSTANTES, resultado);
		map.put(CONSTANTE,new Constante());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_CONSTANTES;

	}
	
	@RequestMapping(value=UrlConstants.URL_LISTADO_CONSTANTES_FILTRADO, method=RequestMethod.GET)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {
		
		List<Constante> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Constante constante = null;
		boolean hayFiltro = true;
		
		int total = 0;
				
		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		
		if(filtro != null && filtro instanceof Constante){
			
			constante = (Constante)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}
		
		paginacion.setInicio(inicio);
			
		
		resultado = constantesService.findConstantesByExample(constante, paginacion);
		total = constantesService.countConstantesByExample(constante);
		
		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);
		
		
		model.addAttribute(CONSTANTE, new Constante());
		model.addAttribute(CONSTANTES, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_CONSTANTES;
	}





}

