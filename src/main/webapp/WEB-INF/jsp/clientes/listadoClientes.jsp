

<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>

<script>
function borrarCliente(idCliente){

	 	document.formulario.idCliente.value = idCliente;
	 	document.formulario.action="borrarCliente.do";
	 	document.formulario.submit();

}
function editarCliente(idCliente){

 	document.formulario.idCliente.value = idCliente;
 	document.formulario.action="editarCliente.do";
 	document.formulario.submit();


}

function mostarConfirmBorrarCliente(idCliente) {
    var message = '<spring:message code="confirmacion.borrar.cliente"/>';

    $("#aceptarBorrar").attr("href", "javascript:borrarCliente(" + idCliente + ");");

    mostrarConfirm(message);
}


function mostrarConfirm(message) {
    $('#alertModal').find('.modal-body p').html(message);
    $('#alertModal').modal('show');
}
</script>


<!-- Warning Modal -->
<div id="alertModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
         <p></p>
      </div>
      <div class="modal-footer">
         <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</button>
         <a class="btn btn-primary btn-sm" role="button" href="#" id="aceptarBorrar"><i class="fas fa-check-circle"></i>&nbsp;&nbsp;<spring:message code="button.aceptar" /></a>
      </div>
    </div>
  </div>
</div>

<!-- ./ Warning Modal -->

<form name="formulario" method="post">
	<input type="hidden" name="idCliente"/>
</form>

		<br>
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
							class="fas fa-users fa-lg mr-2"></i>Listado de clientes</li>
					</ol>
				</nav>
			</div>
		</div>



		<c:if  test="${!empty clientes}">

		<div class="row">
			<div class="col-md-12">
			  <a class="btn btn-secondary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">Buscar</a> <mistags:paginacion accion="listadoclientes" />
			</div>
		</div>
<div class="collapse" id="collapseExample">
  <div class="card card-body">
	<div class="row">
		<div class="col-md-12">
			<!-- Extended default form grid -->
			<form>
				<!-- Grid row -->
				<div class="form-row">
					Buscador
				</div>
				<!-- Grid row -->
				<div class="form-row">
					<div class="form-group col-md-11">
						&nbsp;
					</div>
					<div class="form-group col-md-1">
						<button type="button" class="btn btn-primary btn-sm"><i class="fas fa-save"></i> &nbsp;&nbsp;Buscar</button>
					</div>
				</div>

			</form>
			<!-- Extended default form grid -->
		</div>
	</div>
  </div>
</div>

<br>

	<table class="table table-hover">
		<thead class="blue lighten-4">
			<tr class="bg-light">
	    		
					<th><spring:message code="label.nombre"/></th>
					
					<th><spring:message code="label.apellidos"/></th>
					
					<th><spring:message code="label.telefono"/></th>
					
					<th><spring:message code="label.dni"/></th>
					
					<th><spring:message code="label.direccion"/></th>
					
					<th width="10%">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
		<c:forEach items="${clientes}" var="cliente">
			<tr>
			
				<td>${cliente.nombre}</td>
			
				<td>${cliente.apellidos}</td>
			
				<td>${cliente.telefono}</td>
			
				<td>${cliente.dni}</td>
			
				<td>${cliente.direccion}</td>
			
				<td>
					<div>
							<label data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> <span style="cursor: pointer;"><i
									class="fas fa-cog fa-lg"></i></span>
							</label>
							<ul class="dropdown-menu dropdown-primary">
								<li class="nav-item dropdown"><a class="dropdown-item"
									href="javascript:editarCliente('${cliente.idCliente}')"><spring:message code="label.editar"/></a></li>
								<li class="nav-item dropdown"><a class="dropdown-item"
									href="javascript:mostarConfirmBorrarCliente('${cliente.idCliente}')"><spring:message code="label.borrar"/></a></li>
							</ul>
						</div>
				</td>
			</tr>
		</c:forEach>
			</tbody>
			</table>


		</c:if>

		<c:if  test="${empty clientes}">

			<br>
			<br>
			<br>
			<br>
			<center>No hay clientes que mostrar...</center>
		</c:if>

		<div class="row">
			<div class="col-md-12">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#nuevoFormulario"><i class="fas fa-plus-circle"></i>&nbsp;&nbsp;Nuevo</button>
				<button type="button" class="btn btn-success btn-sm"><i class="fas fa-search"></i> &nbsp;&nbsp;Buscar</button>
			</div>
		</div>


<!-- Modal -->
<div class="modal fade" id="nuevoFormulario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Nueva cliente</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <div class="row">
		<div class="col-md-1">&nbsp;</div>
		<div class="col-md-10">
		<!-- Extended default form grid -->
		<form:form modelAttribute="cliente" action='insertarCliente.do' method="post">
			<!-- Grid row -->
			<div class="form-row">

    		
					<!-- Grid row -->
					<div class="form-row">
						<!-- Default input -->
						<div class="form-group col-md-12">
							<label for="nombre"><spring:message code="label.nombre"/></label> <form:input path="nombre" class="form-control"/>
						</div>
					</div>
					<!-- Grid row -->
					
					<!-- Grid row -->
					<div class="form-row">
						<!-- Default input -->
						<div class="form-group col-md-12">
							<label for="apellidos"><spring:message code="label.apellidos"/></label> <form:input path="apellidos" class="form-control"/>
						</div>
					</div>
					<!-- Grid row -->
					
					<!-- Grid row -->
					<div class="form-row">
						<!-- Default input -->
						<div class="form-group col-md-12">
							<label for="telefono"><spring:message code="label.telefono"/></label> <form:input path="telefono" class="form-control"/>
						</div>
					</div>
					<!-- Grid row -->
					
					<!-- Grid row -->
					<div class="form-row">
						<!-- Default input -->
						<div class="form-group col-md-12">
							<label for="dni"><spring:message code="label.dni"/></label> <form:input path="dni" class="form-control"/>
						</div>
					</div>
					<!-- Grid row -->
					
					<!-- Grid row -->
					<div class="form-row">
						<!-- Default input -->
						<div class="form-group col-md-12">
							<label for="direccion"><spring:message code="label.direccion"/></label> <form:input path="direccion" class="form-control"/>
						</div>
					</div>
					<!-- Grid row -->
					

			<br>


		<!-- Extended default form grid -->
		</div>
		<div class="col-md-1">&nbsp;</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</button>
        <button type="submit" class="btn btn-success btn-sm"><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>
      </div>

      </form:form>
    </div>
  </div>
</div>


