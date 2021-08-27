<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


<script>

// TODO: Reescribir esta funci&oacute;n
	function existeCliente(){
		document.getElementById("dniCliente").value = document.getElementById("inputDNI").value;
		document.tragaldabaForm.submit();
	}

	function terminarPedido() {
		document.getElementById("idsPlatosPedido").value = document.getElementById("idsPlatos").value;
		document.getElementById("datosPedidoParaGrabar").value = document.getElementById("datosPedido").value;

		document.cliente.submit();
	}

<c:if test='${tragaldabaForm.dniCliente eq "-1"}'>
	$( document ).ready(function() {
		document.getElementById("iconoMensaje").innerHTML = '<span><i class="fas fa-exclamation-triangle fa-2x"></i></span>';
		$('#infoParaUsuario').removeClass("alert-warning");
		$('#infoParaUsuario').addClass("alert-danger");
		document.getElementById("mensaje").innerHTML = "No se ha encontrado ning&uacute;n cliente con dni =  <b>${cliente.dni}</b>. Rellene los campos del formulario para continuar.";
	});
</c:if>

</script>

<form:form modelAttribute="tragaldabaForm" action="existeCliente.do" method="post" name="tragaldabaForm">
	<form:hidden path="dniCliente" id="dniCliente"/>
	<form:hidden path="idsPlatos" id="idsPlatos"/>
	<form:hidden path="datosPedido" id="datosPedido"/>
</form:form>


	<form:form modelAttribute="cliente" action='terminarPedido.do' method="post">


		<br>
		<br>
		<br>
		<br>
		
		
		<div class="row">
			<div class="col-md-12">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item active"><i
							class="fas fa-list-alt fa-lg mr-1"></i><i
							class="fas fa-users fa-lg mr-2"></i>Datos del cliente</li>
					</ol>
				</nav>
			</div>
		</div>

	<div class="row alert alert-warning" id="infoParaUsuario">
		<div class="col-md-1" id="iconoMensaje"><span><i class="fas fa-info-circle fa-2x"></i></span></div>
		<div class="col-md-10">				
				<div id="mensaje">- Si ya es un cliente registrado, por favor introduzca su DNI y pulse el bot&oacute;n para cargar sus datos. Puede modificar cualquier dato que haya cambiado
				<br>- Si no est&aacute; registrado, rellene sus datos para terminar de tramitar el pedido.
				<br>Gracias. 
				</div>
		</div>
		<div class="col-md-1">&nbsp;</div>
	</div>

	<br>

	<div class="form-row">
	<div class="form-group col-md-4">
		<label for="dni">DNI</label>
		<div class="input-group">
		    <form:input path="dni" class="form-control" id="inputDNI"/><form:errors path="dni" element="div" id="rojo"/>
		    <span class="input-group-btn" style="width:0;">
		        <button type="button" class="btn btn-primary" onclick="javascript:existeCliente();"><i class="fas fa-arrow-alt-circle-down fa-lg mr-2"></i></button>
		    </span>
		</div>
	</div>
	</div>


	<div class="form-row">
		<div class="form-group col-md-6">
			<label for="nombre">Nombre</label> <form:input path="nombre" class="form-control" id="nombre"/><form:errors path="nombre" element="div" id="rojo"/>
		</div>
		<div class="form-group col-md-6">
			<label for="apellidos">Apellidos</label> <form:input path="apellidos" class="form-control" id="apellidos"/><form:errors path="apellidos" element="div" id="rojo"/>
		</div>
	</div>								
	
	<div class="form-row">
		<div class="form-group col-md-8">
			<label for="direccion">Direcci&oacute;n</label> <form:input path="direccion" class="form-control" id="direccion"/><form:errors path="direccion" element="div" id="rojo"/>
		</div>
		<div class="form-group col-md-4">
			<label for="telefono">Tel&oacute;fono</label> <form:input path="telefono" class="form-control" id="telefono"/><form:errors path="telefono" element="div" id="rojo"/>
		</div>
	</div>									
	
	<br>
	
	
	<div class="row">
		<div class="col text-right">
			<button class="btn btn-primary btn-sm" onclick="terminarPedido()">Terminar pedido</button>
		</div>
	</div>
	<form:hidden path="idCliente"/>
	<input type="hidden" id="idsPlatosPedido" name="idsPlatosPedido">
	<input type="hidden" id="datosPedidoParaGrabar" name="datosPedidoParaGrabar">
</form:form>

<br>
<br>
<br>
<br>
<br>

