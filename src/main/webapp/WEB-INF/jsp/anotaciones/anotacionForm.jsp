
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="anotacion" action='${modo}Anotacion.do' method="post">


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
					<spring:message code="label.alta.anotacion"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.anotacion"/>
					</c:if>
					</li>
				</ol>
			</nav>
		</div>
		</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idAnotacion"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="contenido"><spring:message code="label.contenido"/></label> <form:input path="contenido" class="form-control"/><form:errors path="contenido" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="tipo"><spring:message code="label.tipo"/></label> <form:input path="tipo" class="form-control"/><form:errors path="tipo" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="fecha"><spring:message code="label.fecha"/></label> <form:input path="fecha" class="form-control"/><form:errors path="fecha" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idBitacora"><spring:message code="label.idBitacora"/></label> <form:input path="idBitacora" class="form-control"/><form:errors path="idBitacora" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoAnotaciones1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

