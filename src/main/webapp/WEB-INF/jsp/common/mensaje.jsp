<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>

		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		
		
<c:if test="${not empty mensaje}">
<div class="container">
	<div class="row alert alert-success justify-content-center">
		<div class="col-4">
				<span><i class="fas fa-info-circle fa-2x align-bottom"></i></span>&nbsp;&nbsp;&nbsp;
				<span class="agradecimiento"><spring:message code="${mensaje}" /></span>
				<br><br>
		</div>
	</div>
</div>	
<br><br><br>	
</c:if>