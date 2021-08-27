package es.golemdr.tragaldaba.controller;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
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
import es.golemdr.tragaldaba.domain.Proveedor;
import es.golemdr.tragaldaba.domain.form.ProveedorForm;
import es.golemdr.tragaldaba.ext.Constantes;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionBean;
import es.golemdr.tragaldaba.ext.utils.paginacion.PaginacionFactory;
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
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = proveedoresService.getProveedores(paginacion);

		map.put("paginacion", paginacion);
		map.put(PROVEEDORES, resultado);
		map.put(PROVEEDOR,new ProveedorForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PROVEEDORES;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_PROVEEDOR)
	public String insertar(ProveedorForm formulario, Model model) {

		Proveedor entity = new Proveedor();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		proveedoresService.insertarActualizarProveedor(entity);

		return ForwardConstants.RED_LISTADO_PROVEEDORES; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_PROVEEDOR)
	public String editar(String idProveedor, Map<String, Object> map) {

		Proveedor entity = proveedoresService.getById(new Long(idProveedor));

		ProveedorForm formulario = new ProveedorForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(PROVEEDOR,formulario);

		return ForwardConstants.FWD_PROVEEDOR_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_PROVEEDOR)
	public String actualizar(@Valid ProveedorForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_PROVEEDOR_FORM;

		}else{

			Proveedor entity = new Proveedor();

			try {

				BeanUtils.copyProperties(entity, formulario);

				proveedoresService.insertarActualizarProveedor(entity);

				destino = ForwardConstants.RED_LISTADO_PROVEEDORES;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_PROVEEDOR)
	public String borrar(String idProveedor, Map<String, Object> map) {

		proveedoresService.borrarProveedor(new Long(idProveedor));

		return ForwardConstants.RED_LISTADO_PROVEEDORES;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_PROVEEDORES)
	public String buscarProveedores(ProveedorForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Proveedor> resultado = null;

		Proveedor entity = new Proveedor();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = proveedoresService.findProveedoresByExample(entity, paginacion);
			total = proveedoresService.countProveedoresByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(PROVEEDORES, resultado);
		map.put(PROVEEDOR,new ProveedorForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PROVEEDORES;

	}

	@RequestMapping(value=UrlConstants.URL_LISTADO_PROVEEDORES_FILTRADO, method=RequestMethod.GET)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Proveedor> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Proveedor proveedor = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro != null && filtro instanceof Proveedor){

			proveedor = (Proveedor)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = proveedoresService.findProveedoresByExample(proveedor, paginacion);
		total = proveedoresService.countProveedoresByExample(proveedor);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(PROVEEDOR, new ProveedorForm());
		model.addAttribute(PROVEEDORES, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PROVEEDORES;
	}



}

