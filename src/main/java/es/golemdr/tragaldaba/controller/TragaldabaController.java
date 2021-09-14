package es.golemdr.tragaldaba.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.golemdr.tragaldaba.controller.constantes.ForwardConstants;
import es.golemdr.tragaldaba.controller.constantes.UrlConstants;
import es.golemdr.tragaldaba.domain.Cliente;
import es.golemdr.tragaldaba.domain.Constante;
import es.golemdr.tragaldaba.domain.Pedido;
import es.golemdr.tragaldaba.domain.PlatoMongo;
import es.golemdr.tragaldaba.ext.utils.tools.ConstanteDomainUtils;
import es.golemdr.tragaldaba.ext.utils.tools.PreparadorPedidos;
import es.golemdr.tragaldaba.form.TragaldabaForm;
import es.golemdr.tragaldaba.service.ClientesService;
import es.golemdr.tragaldaba.service.ConstantesService;
import es.golemdr.tragaldaba.service.PedidosService;
import es.golemdr.tragaldaba.service.PlatosMongoService;


@Controller
public class TragaldabaController {
	
	private static Logger log = LogManager.getLogger(TragaldabaController.class);
	
	private static final String PLATOS = "platos";
	private static final String PEDIDO = "pedido";
	private static final String FAMILIA_PLATOS = "Platos";
	private static final String CONSTANTES = "constantes";
	
//	@Autowired
//	private PlatosService platosService;
	
	@Autowired
	private PlatosMongoService platosMongoService;

	
	@Autowired
	private ConstantesService constantesService;
	
	@Autowired
	private ClientesService clientesService;
	
	@Autowired
	private PedidosService pedidosService;

	@GetMapping(value=UrlConstants.URL_REALIZAR_PEDIDO)
	public String list(Map<String, Object> map, HttpServletRequest request) {
		
		List<PlatoMongo> platos = platosMongoService.getPlatos();
		List<Constante> todasConstantes =  constantesService.getConstantes();
		List<Constante> contantesFiltradas = ConstanteDomainUtils.filtrarPorFamilia(FAMILIA_PLATOS, todasConstantes);
		
		map.put(PLATOS, platos);
		map.put(CONSTANTES, contantesFiltradas);

		return ForwardConstants.FWD_REALIZAR_PEDIDO;
	}
	
	
	@PostMapping(value=UrlConstants.URL_CONFIRMAR_PEDIDO)
	public String confirmarPedido(Map<String, Object> map, HttpServletRequest request) {
		TragaldabaForm tragaldabaForm = new TragaldabaForm();
		List<String> platos = new ArrayList<String>();
		
		
		Enumeration<String> params = request.getParameterNames();
		String platoSelected = null;
		String idPlato = null;

		while (params.hasMoreElements()) {
			platoSelected = params.nextElement();
			
			if (platoSelected.endsWith("_selected")) {
				idPlato = request.getParameter(platoSelected);
				if (idPlato != null && idPlato.trim().length() != 0) {
					platos.add(idPlato);
				}
			}
		}
		
		Double total = Double.parseDouble(request.getParameter("totalPedido"));
		Long numArticulos = Long.valueOf(platos.size());
		
		tragaldabaForm.setDniCliente("");
		tragaldabaForm.setIdsPlatos(platos.toString());
		tragaldabaForm.setDatosPedido("{'numArticulos':" + numArticulos + ", 'total':"+ total + "}");

		map.put("tragaldabaForm", tragaldabaForm);
		map.put("cliente", new Cliente());

		return ForwardConstants.FWD_CONFIRMAR_PEDIDO;
	}	
	
	
	@PostMapping(value=UrlConstants.URL_TERMINAR_PEDIDO)
	public String terminarPedido(@Valid Cliente cliente, 
			@RequestParam(name = "idsPlatosPedido") String idsPlatosPedido, 
			@RequestParam(name = "datosPedidoParaGrabar") String datosPedidoParaGrabar, 
			Map<String, Object> map,
			BindingResult result,
			HttpServletRequest request) {
		
		if (result.hasErrors()) {
			map.put("mensaje", "pedido.ko");
		} else {
			Cliente clienteAux = clientesService.insertarActualizarCliente(cliente);
	

			List<String> listaIdsPlatos = PreparadorPedidos.stringToList(idsPlatosPedido);
			List<PlatoMongo> platosMongo = new ArrayList<>();
			PlatoMongo plato = null;
			for(String idPlato : listaIdsPlatos) {
				plato = platosMongoService.getById(Long.parseLong(idPlato));
				platosMongo.add(plato);
			}
			
			// Traspasamos la info del formulario al objeto pedido que vamos a grabar
			Pedido pedidoAux = PreparadorPedidos.extraerDatos(datosPedidoParaGrabar);
			Pedido pedido = new Pedido();
			
			pedido.setNumArticulos(pedidoAux.getNumArticulos());
			pedido.setTotal(pedidoAux.getTotal());
			pedido.setPlatosFromPlatosMongo(platosMongo);
			
			pedido.setFecha(new Date(System.currentTimeMillis()));
			
			pedido.setCliente(clienteAux);
			
			// Proovedor enchufado a "fuego"
			pedido.setIdProveedor(new Long(1)); 
			
			// Tipo de entrega va a "fuego"
			pedido.setTipoEntrega(new Long(1));
			
			pedidosService.insertarActualizarPedido(pedido);
			
			
			map.put("mensaje", "pedido.ok");
		}
		

		
		return ForwardConstants.FWD_TERMINAR_PEDIDO;
	}
	
	
	@PostMapping(value = UrlConstants.URL_EXISTE_CLIENTE)
	public String existeCliente(@ModelAttribute("tragaldabaForm") TragaldabaForm tragaldabaForm, Map<String, Object> map, HttpServletRequest request) {

		Cliente resultado = clientesService.existeCliente(tragaldabaForm.getDniCliente().toUpperCase());
		
		if (resultado == null) {
			resultado = new Cliente();
			resultado.setDni(tragaldabaForm.getDniCliente());
			tragaldabaForm.setDniCliente("-1");
		}
		
		map.put("cliente", resultado);
		map.put("tragaldabaForm", tragaldabaForm);
		
		return ForwardConstants.FWD_CONFIRMAR_PEDIDO;

	}

	
	
}

