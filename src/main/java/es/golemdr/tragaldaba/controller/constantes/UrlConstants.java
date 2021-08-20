package es.golemdr.tragaldaba.controller.constantes;


public class UrlConstants {

	public static final String SUFIJO_PAGINACION = 	"{inicio}.do";
	public static final String URL_LOGIN = "/login.do";
	public static final String URL_DENIED = "/denied.do";
	public static final String URL_LOGIN_CONTROLLER = "/loginController.do";
	public static final String URL_LOGIN_FAILURE = "/login-failure.do";	
	public static final String URL_HOME = "/home.do";
	public static final String URL_ACCESO_OK = "/accesook.do";
	public static final String URL_ENTRADA_APLICACION = "/inicio.do";


	public static final String URL_VER_LOGIN = "/login.do";
	public static final String URL_CAMBIAR_PASSWORD="/cambiarPassword.do";
	
	public static final String SEGURIDAD_CLIENTES =  "/sec";	
	public static final String SEGURIDAD_CONSTANTES =  "/sec";	
	public static final String SEGURIDAD_PEDIDOS =  "/sec";	
	public static final String SEGURIDAD_PLATOS =  "/sec";	
	public static final String SEGURIDAD_PROVEEDORES =  "/sec";		

	

	// Clientes
	public static final String URL_LISTADO_CLIENTES = SEGURIDAD_CLIENTES + "/listadoClientes" + SUFIJO_PAGINACION;	
	public static final String URL_ALTA_CLIENTE = SEGURIDAD_CLIENTES + "/altaCliente.do";
	public static final String URL_INSERTAR_CLIENTE = SEGURIDAD_CLIENTES + "/insertarCliente.do";
	public static final String URL_EDITAR_CLIENTE = SEGURIDAD_CLIENTES + "/editarCliente.do";
	public static final String URL_ACTUALIZAR_CLIENTE = SEGURIDAD_CLIENTES + "/actualizarCliente.do";
	public static final String URL_BORRAR_CLIENTE = SEGURIDAD_CLIENTES + "/borrarCliente.do";


	

	// Constantes
	public static final String URL_LISTADO_CONSTANTES = SEGURIDAD_CONSTANTES + "/listadoConstantes" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_CONSTANTES_FILTRADO = SEGURIDAD_CONSTANTES + "/listadoConstantesFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_CONSTANTE = SEGURIDAD_CONSTANTES + "/altaConstante.do";
	public static final String URL_INSERTAR_CONSTANTE = SEGURIDAD_CONSTANTES + "/insertarConstante.do";
	public static final String URL_EDITAR_CONSTANTE = SEGURIDAD_CONSTANTES + "/editarConstante.do";
	public static final String URL_ACTUALIZAR_CONSTANTE = SEGURIDAD_CONSTANTES + "/actualizarConstante.do";
	public static final String URL_BORRAR_CONSTANTE = SEGURIDAD_CONSTANTES + "/borrarConstante.do";
	
	public static final String URL_VER_BUSQUEDA_CONSTANTE = SEGURIDAD_CONSTANTES + "/verBusquedaConstante.do";
	public static final String URL_BUSCAR_CONSTANTES = SEGURIDAD_CONSTANTES + "/buscarConstantes.do";


	

	// Pedidos
	public static final String URL_LISTADO_PEDIDOS = SEGURIDAD_PEDIDOS + "/listadoPedidos" + SUFIJO_PAGINACION;	
	public static final String URL_ALTA_PEDIDO = SEGURIDAD_PEDIDOS + "/altaPedido.do";
	public static final String URL_INSERTAR_PEDIDO = SEGURIDAD_PEDIDOS + "/insertarPedido.do";
	public static final String URL_EDITAR_PEDIDO = SEGURIDAD_PEDIDOS + "/editarPedido.do";
	public static final String URL_ACTUALIZAR_PEDIDO = SEGURIDAD_PEDIDOS + "/actualizarPedido.do";
	public static final String URL_BORRAR_PEDIDO = SEGURIDAD_PEDIDOS + "/borrarPedido.do";


	

	// Platos
	public static final String URL_LISTADO_PLATOS = SEGURIDAD_PLATOS + "/listadoPlatos" + SUFIJO_PAGINACION;	
	public static final String URL_ALTA_PLATO = SEGURIDAD_PLATOS + "/altaPlato.do";
	public static final String URL_INSERTAR_PLATO = SEGURIDAD_PLATOS + "/insertarPlato.do";
	public static final String URL_EDITAR_PLATO = SEGURIDAD_PLATOS + "/editarPlato.do";
	public static final String URL_ACTUALIZAR_PLATO = SEGURIDAD_PLATOS + "/actualizarPlato.do";
	public static final String URL_BORRAR_PLATO = SEGURIDAD_PLATOS + "/borrarPlato.do";


	

	// Proveedores
	public static final String URL_LISTADO_PROVEEDORES = SEGURIDAD_PROVEEDORES + "/listadoProveedores" + SUFIJO_PAGINACION;	
	public static final String URL_ALTA_PROVEEDOR = SEGURIDAD_PROVEEDORES + "/altaProveedor.do";
	public static final String URL_INSERTAR_PROVEEDOR = SEGURIDAD_PROVEEDORES + "/insertarProveedor.do";
	public static final String URL_EDITAR_PROVEEDOR = SEGURIDAD_PROVEEDORES + "/editarProveedor.do";
	public static final String URL_ACTUALIZAR_PROVEEDOR = SEGURIDAD_PROVEEDORES + "/actualizarProveedor.do";
	public static final String URL_BORRAR_PROVEEDOR = SEGURIDAD_PROVEEDORES + "/borrarProveedor.do";


	

}
