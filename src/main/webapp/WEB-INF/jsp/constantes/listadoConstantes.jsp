

<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>

<script>
function borrarConstante(idConstante){

	 	document.formulario.idConstante.value = idConstante;
	 	document.formulario.action="borrarConstante.do";
	 	document.formulario.submit();

}
function editarConstante(idConstante){

 	document.formulario.idConstante.value = idConstante;
 	document.formulario.action="editarConstante.do";
 	document.formulario.submit();


}

function mostarConfirmBorrarConstante(idConstante) {
    var message = '<spring:message code="confirmacion.borrar.constante"/>';

    $("#aceptarBorrar").attr("href", "javascript:borrarConstante(" + idConstante + ");");

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
			<div class="modal-header  bg-danger">
				<h5 class="modal-title text-white">
					<i class="fas fa-exclamation-triangle fa-sm"></i>&nbsp;&nbsp;&#161;Atenci&oacute;n&#33;
				</h5>
				<button class="close text-white" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<p class="text-danger"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btn-sm"
					data-dismiss="modal">
					<i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar
				</button>
				<a class="btn btn-danger btn-sm" role="button" href="#"
					id="aceptarBorrar"><i class="fas fa-check-circle"></i>&nbsp;&nbsp;<spring:message
						code="button.aceptar" /></a>
			</div>
		</div>
	</div>
</div>

<!-- ./ Warning Modal -->

<form name="formulario" method="post">
	<input type="hidden" name="idConstante"/>
</form>

		<br>
		<br>
		<br>


		<div class="row">
			<div class="col-md-12">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item active">
							<i class="fas fa-list-alt fa-lg mr-1"></i>
							<i class="fas fa-cube fa-lg mr-2"></i>
							Listado de constantes
						</li>
					</ol>
				</nav>
			</div>
		</div>


<div class="row">
	<div class="col-md-8"></div>

	<c:if test="${hayFiltro eq false}">
		<mistags:paginacion accion="listadoConstantes" />
	</c:if>

	<c:if test="${hayFiltro eq true}">
		<mistags:paginacion accion="listadoConstantesFiltrado" />
	</c:if>
</div>


<c:if  test="${!empty constantes}">


	<table class="table table-hover">
		<thead class="blue lighten-4">
			<tr class="bg-light">
	    		
					<th><spring:message code="label.familia"/></th>
					
					<th><spring:message code="label.atributo"/></th>
					
					<th><spring:message code="label.literal"/></th>
					
					<th><spring:message code="label.valor"/></th>
					
					<th><spring:message code="label.activa"/></th>
					
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
		<c:forEach items="${constantes}" var="constante">
			<tr>
			
				<td>${constante.familia}</td>
			
				<td>${constante.atributo}</td>
			
				<td>${constante.literal}</td>
			
				<td>
					<div class="text-right">
						${constante.valor}
					</div>
				</td>
			
				<td>
					<div class="text-center">
						<c:if test="${constante.activa eq 1}">
							<i class="fas fa-check fa-lg text-success"></i>
						</c:if>
						<c:if test="${constante.activa eq 0}">
							<i class="fas fa-times fa-lg text-danger"></i>
						</c:if>
					</div>				
				</td>
			
				<td>
					<div class="text-center">
							<label data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span style="cursor: pointer;"><i
									class="fas fa-cog fa-lg"></i></span>
							</label>
							<ul class="dropdown-menu dropdown-primary">
								<li class="nav-item dropdown"><a class="dropdown-item"
									href="javascript:editarConstante('${constante.idConstante}')"><spring:message code="label.editar"/></a></li>
								<li class="nav-item dropdown"><a class="dropdown-item"
									href="javascript:mostarConfirmBorrarConstante('${constante.idConstante}')"><spring:message code="label.borrar"/></a></li>
							</ul>
						</div>
				</td>
			</tr>
		</c:forEach>
			</tbody>
			</table>


		</c:if>

		<c:if  test="${empty constantes}">

			<br>
			<br>
			<br>
			<br>
			<center>No hay constantes que mostrar...</center>
		</c:if>

		<div class="row">
			<div class="col-md-12">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#nuevoFormulario"><i class="fas fa-plus-circle"></i>&nbsp;&nbsp;Nuevo</button>				
				<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#ventanaBuscar"><i class="fas fa-search"></i> &nbsp;&nbsp;Buscar</button>
			</div>
		</div>


<!-- Modal Nueva Constante-->
<div class="modal fade" id="nuevoFormulario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0" style="background-color: #e9ecef; color: #6c757d">
        <span class="modal-title" id="exampleModalLabel"><i class="fas fa-plus-circle fa-lg pr-1"></i><i class="fas fa-cube fa-lg pr-2"></i>Nueva Constante</span>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="color: #6c757d">
		<form:form modelAttribute="constante" action='insertarConstante.do' method="post">

			<div class="form-group">
				<label for="familia">Familia</label> <form:input path="familia" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="atributo">Atributo</label> <form:input path="atributo" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="literal">Literal</label> <form:input path="literal" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="valor">Valor</label> <form:input path="valor" class="form-control"/>
			</div>

			<br>	

	      <div class="custom-control custom-checkbox mr-sm-2">
	        <form:checkbox path="activa"  value="1" class="custom-control-input" id="customControlAutosizing"/>
	        <label class="custom-control-label" for="customControlAutosizing">Activa</label>
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
		<form:form modelAttribute="constante" action='buscarConstantes.do' method="post" id="formularioBuscar">
			<div class="form-group">
				<label for="familia">Familia</label> <form:input path="familia" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="atributo">Atributo</label> <form:input path="atributo" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="literal">Literal</label> <form:input path="literal" class="form-control"/>
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
