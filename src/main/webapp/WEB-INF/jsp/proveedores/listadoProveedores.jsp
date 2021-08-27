

<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>

<script>
function borrarProveedor(idProveedor){

	 	document.formulario.idProveedor.value = idProveedor;
	 	document.formulario.action="borrarProveedor.do";
	 	document.formulario.submit();

}
function editarProveedor(idProveedor){

 	document.formulario.idProveedor.value = idProveedor;
 	document.formulario.action="editarProveedor.do";
 	document.formulario.submit();


}

function mostarConfirmBorrarProveedor(idProveedor) {
    var message = '<spring:message code="confirmacion.borrar.proveedor"/>';

    $("#aceptarBorrar").attr("href", "javascript:borrarProveedor(" + idProveedor + ");");

    mostrarConfirm(message);
}


function mostrarConfirm(message) {
    $('#alertModal').find('.modal-body p').html(message);
    $('#alertModal').modal('show');
}

$(document).ready(function(){
    $('#ventanaBuscar').on('hidden.bs.modal', function () {
        $('form[id="formularioBuscar"]').trigger('reset');
    });
});


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
	<input type="hidden" name="idProveedor"/>
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
							class="fas fa-users fa-lg mr-2"></i>Listado de proveedores</li>
					</ol>
				</nav>
			</div>
		</div>



<c:if  test="${!empty proveedores}">


<div class="row">
	<div class="col-md-8"></div>

	<c:if test="${hayFiltro eq false}">
		<mistags:paginacion accion="listadoProveedores" />
	</c:if>

	<c:if test="${hayFiltro eq true}">
		<mistags:paginacion accion="listadoProveedoresFiltrado" />
	</c:if>
</div>


<br>

	<table class="table table-hover">
		<thead class="blue lighten-4">
			<tr class="bg-light">
	    		
					<th scope="col"><spring:message code="label.razonSocial"/></th>
					
					<th scope="col"><spring:message code="label.direccion"/></th>
					
					<th scope="col"><spring:message code="label.localidad"/></th>
					
					<th scope="col"><spring:message code="label.telefono"/></th>
					
					<th scope="col">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
		<c:forEach items="${proveedores}" var="proveedor">
			<tr>
			
				<td>${proveedor.razonSocial}</td>
			
				<td>${proveedor.direccion}</td>
			
				<td>${proveedor.localidad}</td>
			
				<td>${proveedor.telefono}</td>
			
				<td>
					<div>
							<label data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> <span style="cursor: pointer;"><i
									class="fas fa-cog fa-lg"></i></span>
							</label>
							<ul class="dropdown-menu dropdown-primary">
								<li class="nav-item dropdown"><a class="dropdown-item"
									href="javascript:editarProveedor('${proveedor.idProveedor}')"><spring:message code="label.editar"/></a></li>
								<li class="nav-item dropdown"><a class="dropdown-item"
									href="javascript:mostarConfirmBorrarProveedor('${proveedor.idProveedor}')"><spring:message code="label.borrar"/></a></li>
							</ul>
						</div>
				</td>
			</tr>
		</c:forEach>
			</tbody>
			</table>


		</c:if>

		<c:if  test="${empty proveedores}">

			<br>
			<br>
			<br>
			<br>
			<div class="text-center">No hay proveedores que mostrar...</div>
		</c:if>

		<div class="row">
			<div class="col-md-12">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#nuevoFormulario"><i class="fas fa-plus-circle"></i>&nbsp;&nbsp;Nuevo</button>
				<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#ventanaBuscar"><i class="fas fa-search"></i> &nbsp;&nbsp;Buscar</button>
			</div>
		</div>


<!-- Modal Nuev@ proveedor-->
<div class="modal fade" id="nuevoFormulario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0" style="background-color: #e9ecef; color: #6c757d">
        <span class="modal-title" id="exampleModalLabel"><i class="fas fa-plus-circle fa-lg pr-1"></i><i class="fas fa-cube fa-lg pr-2"></i>Nueva proveedor</span>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="color: #6c757d">
		<form:form modelAttribute="proveedor" action="insertarProveedor.do" method="post">

					<div class="form-group">
						<label for="razonSocial"><spring:message code="label.razonSocial"/></label> <form:input path="razonSocial" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="direccion"><spring:message code="label.direccion"/></label> <form:input path="direccion" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="codPostal"><spring:message code="label.codPostal"/></label> <form:input path="codPostal" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="localidad"><spring:message code="label.localidad"/></label> <form:input path="localidad" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="telefono"><spring:message code="label.telefono"/></label> <form:input path="telefono" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="email"><spring:message code="label.email"/></label> <form:input path="email" class="form-control"/>
					</div>

					

			<br>


      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</button>
        <button type="submit" class="btn btn-success btn-sm"><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>
      </div>

      </form:form>
    </div>
  </div>
</div>
</div>



<!-- Modal Busqueda -->
<div class="modal fade" id="ventanaBuscar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0" style="background-color: #e9ecef; color: #6c757d">
        <span class="modal-title" id="exampleModalLabel"><i class="fas fas fa-search fa-lg pr-1"></i><i class="fas fa-cube fa-lg pr-2"></i>Buscar Constante</span>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="color: #6c757d">
		<form:form modelAttribute="proveedor" action='buscarProveedores.do' method="post" id="formularioBuscar">

					<div class="form-group">
						<label for="razonSocial"><spring:message code="label.razonSocial"/></label> <form:input path="razonSocial" class="form-control"/>
					</div>


					<div class="form-group">
						<label for="codPostal"><spring:message code="label.codPostal"/></label> <form:input path="codPostal" class="form-control"/>
					</div>


					<div class="form-group">
						<label for="email"><spring:message code="label.email"/></label> <form:input path="email" class="form-control"/>
					</div>

			<br>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</button>
        <button type="submit" class="btn btn-success btn-sm"><i class="fas fa-save"></i> &nbsp;&nbsp;Buscar</button>
      </div>

      </form:form>
      </div>
    </div>
  </div>
</div>




