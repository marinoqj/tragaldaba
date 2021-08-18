
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="plato" action='${modo}Plato.do' method="post">


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
					<spring:message code="label.alta.plato"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.plato"/>
					</c:if>
					</li>
				</ol>
			</nav>
		</div>
		</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idPlato"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="nombre"><spring:message code="label.nombre"/></label> <form:input path="nombre" class="form-control"/><form:errors path="nombre" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="precio"><spring:message code="label.precio"/></label> <form:input path="precio" class="form-control"/><form:errors path="precio" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="nombreFoto"><spring:message code="label.nombreFoto"/></label> <form:input path="nombreFoto" class="form-control"/><form:errors path="nombreFoto" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="tipo"><spring:message code="label.tipo"/></label> <form:input path="tipo" class="form-control"/><form:errors path="tipo" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoPlatos1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

