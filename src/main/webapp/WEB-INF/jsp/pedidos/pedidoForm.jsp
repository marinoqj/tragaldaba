
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="pedido" action='${modo}Pedido.do' method="post">


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
						class="fas fa-users fa-lg mr-2"></i>
					<c:if test="${modo == 'insertar'}">
					<spring:message code="label.alta.pedido"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.pedido"/>
					</c:if>
					</li>
				</ol>
			</nav>
		</div>
		</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idPedido"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="numArticulos"><spring:message code="label.numArticulos"/></label> <form:input path="numArticulos" class="form-control"/><form:errors path="numArticulos" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="total"><spring:message code="label.total"/></label> <form:input path="total" class="form-control"/><form:errors path="total" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="fecha"><spring:message code="label.fecha"/></label> <form:input path="fecha" class="form-control"/><form:errors path="fecha" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="tipoEntrega"><spring:message code="label.tipoEntrega"/></label> <form:input path="tipoEntrega" class="form-control"/><form:errors path="tipoEntrega" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idCliente"><spring:message code="label.idCliente"/></label> <form:input path="idCliente" class="form-control"/><form:errors path="idCliente" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idProveedor"><spring:message code="label.idProveedor"/></label> <form:input path="idProveedor" class="form-control"/><form:errors path="idProveedor" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoPedidos1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

