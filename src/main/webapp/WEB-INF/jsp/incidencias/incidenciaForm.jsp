
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="incidencia" action='${modo}Incidencia.do' method="post">


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
					<spring:message code="label.alta.incidencia"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.incidencia"/>
					</c:if>
					</li>
				</ol>
			</nav>
		</div>
		</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idIncidencia"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="tipo"><spring:message code="label.tipo"/></label> <form:input path="tipo" class="form-control"/><form:errors path="tipo" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="resumen"><spring:message code="label.resumen"/></label> <form:input path="resumen" class="form-control"/><form:errors path="resumen" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="descripcion"><spring:message code="label.descripcion"/></label> <form:input path="descripcion" class="form-control"/><form:errors path="descripcion" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="prioridad"><spring:message code="label.prioridad"/></label> <form:input path="prioridad" class="form-control"/><form:errors path="prioridad" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idSprint"><spring:message code="label.idSprint"/></label> <form:input path="idSprint" class="form-control"/><form:errors path="idSprint" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idProyecto"><spring:message code="label.idProyecto"/></label> <form:input path="idProyecto" class="form-control"/><form:errors path="idProyecto" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoIncidencias1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

