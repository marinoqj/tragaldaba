
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


<script>

function check(ckeckboxName) {
	var iconId = "icon-" + ckeckboxName;
	var className = document.getElementById(iconId).className;
	var isChecked = (className === "fas fa-check-square");
	

    if (isChecked) {
    	document.getElementById(iconId).className = "far fa-square";
        document.getElementById(ckeckboxName).value = "0";
    } else {
    	document.getElementById(iconId).className = "fas fa-check-square";
        document.getElementById(ckeckboxName).value = "1";
    }
    
}

</script>

	<form:form modelAttribute="constante" action='${modo}Constante.do' method="post">


	<br>
	<br>
	<br>



	<div class="row">
		<div class="col-md-12">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item active">
					<c:if test="${modo == 'insertar'}">
					<i class="fas fa-plus-circle"></i>
					<i class="fas fa-cube fa-lg mr-1"></i>
					<spring:message code="label.alta.constante"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<i class="fas fa-pencil-alt fa-lg mr-1"></i>
					<i class="fas fa-cube fa-lg mr-1"></i>
					<spring:message code="label.editar.constante"/>
					</c:if>
					
					</li>
				</ol>
			</nav>
		</div>
	</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idConstante"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="familia"><spring:message code="label.familia"/></label> <form:input path="familia" class="form-control"/><form:errors path="familia" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="atributo"><spring:message code="label.atributo"/></label> <form:input path="atributo" class="form-control"/><form:errors path="atributo" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="literal"><spring:message code="label.literal"/></label> <form:input path="literal" class="form-control"/><form:errors path="literal" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="valor"><spring:message code="label.valor"/></label> <form:input path="valor" class="form-control"/><form:errors path="valor" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
									      <div class="custom-control custom-checkbox mr-sm-2">
									        <form:checkbox path="activa"  value="1" class="custom-control-input" id="customControlAutosizing"/>
									        <label class="custom-control-label" for="customControlAutosizing">Activa</label>									        
									      </div>										
									</div>
								</div>
					


							<a href="./listadoConstantes1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>




	</form:form>

<c:if test="${constante.activa == '1'}">
	<script>
	
	document.getElementById("icon-activa").className = "fas fa-check-square";
    document.getElementById("activa").value = "1";

	</script>
</c:if>