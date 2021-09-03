
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="sprint" action='${modo}Sprint.do' method="post">


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
					<spring:message code="label.alta.sprint"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.sprint"/>
					</c:if>
					</li>
				</ol>
			</nav>
		</div>
		</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idSprint"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="nombre"><spring:message code="label.nombre"/></label> <form:input path="nombre" class="form-control"/><form:errors path="nombre" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="meta"><spring:message code="label.meta"/></label> <form:input path="meta" class="form-control"/><form:errors path="meta" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="duracion"><spring:message code="label.duracion"/></label> <form:input path="duracion" class="form-control"/><form:errors path="duracion" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="fechaInicio"><spring:message code="label.fechaInicio"/></label> <form:input path="fechaInicio" class="form-control"/><form:errors path="fechaInicio" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="fechaFin"><spring:message code="label.fechaFin"/></label> <form:input path="fechaFin" class="form-control"/><form:errors path="fechaFin" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idProyecto"><spring:message code="label.idProyecto"/></label> <form:input path="idProyecto" class="form-control"/><form:errors path="idProyecto" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoSprints1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

