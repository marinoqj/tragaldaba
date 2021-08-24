package es.golemdr.tragaldaba.controller;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
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
import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.domain.Plato;
import es.golemdr.tragaldaba.domain.form.PlatoForm;
import es.golemdr.tragaldaba.ext.Constantes;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionFactory;
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
		boolean hayFiltro = false;
		
		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = platosService.getConstantes(paginacion);
		
		map.put("paginacion", paginacion);
		map.put(PLATOS, resultado);
		map.put(PLATO,new PlatoForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PLATOS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_PLATO)
	public String insertar(PlatoForm formulario, Model model) {

		Plato entity = new Plato();
		
		try {
			
			BeanUtils.copyProperties(entity, formulario);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}
		
		
		platosService.insertarActualizarPlato(entity);

		return ForwardConstants.RED_LISTADO_PLATOS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_PLATO)
	public String editar(String idPlato, Map<String, Object> map) {

		Plato entity = platosService.getById(new Long(idPlato));
		
		PlatoForm formulario = new PlatoForm();
		
		try {
			
			BeanUtils.copyProperties(formulario, entity);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}
		

		map.put("modo", "actualizar");
		map.put(PLATO,formulario);

		return ForwardConstants.FWD_PLATO_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_PLATO)
	public String actualizar(@Valid PlatoForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_PLATO_FORM;

		}else{
			
			Plato entity = new Plato();
			
			try {
				
				BeanUtils.copyProperties(entity, formulario);

				platosService.insertarActualizarPlato(entity);
				
				destino = ForwardConstants.RED_LISTADO_PLATOS;
				
				
			} catch (IllegalAccessException | InvocationTargetException e) {
				
				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_PLATO)
	public String borrar(String idPlato, Map<String, Object> map) {

		platosService.borrarPlato(new Long(idPlato));

		return ForwardConstants.RED_LISTADO_PLATOS;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_PLATOS)
	public String buscarPlatos(PlatoForm formulario, Map<String, Object> map, HttpServletRequest request) {
		
		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;
		
		int total = 0;
		
		paginacion.setInicio(0);
		
		List<Plato> resultado = null;
		
		Plato entity = new Plato();
		
		try {
			
			BeanUtils.copyProperties(entity, formulario);
			
			resultado = platosService.findPlatosByExample(entity, paginacion);
			total = platosService.countPlatosByExample(entity);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}
				
		if(total > paginacion.getElementosXpagina()){
			
			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);
			

		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);
		
		map.put(PLATOS, resultado);
		map.put(PLATO,new PlatoForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PLATOS;

	}
	
	@GetMapping(value=UrlConstants.URL_LISTADO_PLATOS_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {
		
		List<Plato> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Plato plato = null;
		boolean hayFiltro = true;
		
		int total = 0;
				
		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		
		if(filtro instanceof Plato){
			
			plato = (Plato)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}
		
		paginacion.setInicio(inicio);
			
		
		resultado = platosService.findPlatosByExample(plato, paginacion);
		total = platosService.countPlatosByExample(plato);
		
		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);
		
		
		model.addAttribute(PLATO, new PlatoForm());
		model.addAttribute(PLATOS, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PLATOS;
	}


	

}

