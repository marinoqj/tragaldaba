package es.golemdr.tragaldaba.controller.constantes;


public class ForwardConstants {
	
	private ForwardConstants() {
		throw new IllegalStateException("ForwardConstants class");
	}
	
	
	public static final String FWD_HOME = "Home";
	public static final String FWD_LOGIN = "Login";
	public static final String FWD_ACCESO_DENEGADO = "AccesoDenegado";
	public static final String FWD_MENSAJE = "Mensaje";
	
	// Clientes
	public static final String FWD_LISTADO_CLIENTES = "ListadoClientes";	
	public static final String RED_LISTADO_CLIENTES = "redirect:listadoClientes1.do";
	public static final String FWD_CLIENTE_FORM = "ClienteForm";
	
	// Constantes
	public static final String FWD_LISTADO_CONSTANTES = "ListadoConstantes";	
	public static final String RED_LISTADO_CONSTANTES = "redirect:listadoConstantes1.do";
	public static final String FWD_CONSTANTE_FORM = "ConstanteForm";
	
	// Pedidos
	public static final String FWD_LISTADO_PEDIDOS = "ListadoPedidos";	
	public static final String RED_LISTADO_PEDIDOS = "redirect:listadoPedidos1.do";
	public static final String FWD_PEDIDO_FORM = "PedidoForm";
	
	// Platos
	public static final String FWD_LISTADO_PLATOS = "ListadoPlatos";	
	public static final String RED_LISTADO_PLATOS = "redirect:listadoPlatos1.do";
	public static final String FWD_PLATO_FORM = "PlatoForm";
	
	// Proveedores
	public static final String FWD_LISTADO_PROVEEDORES = "ListadoProveedores";	
	public static final String RED_LISTADO_PROVEEDORES = "redirect:listadoProveedores1.do";
	public static final String FWD_PROVEEDOR_FORM = "ProveedorForm";
	
	
	
	public static final String FWD_REALIZAR_PEDIDO = "RealizarPedido";
	public static final String FWD_CONFIRMAR_PEDIDO = "ConfirmarPedido";
	public static final String FWD_TERMINAR_PEDIDO = "TerminarPedido";
	

}
