
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="evento" action='${modo}Evento.do' method="post">


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
					<spring:message code="label.alta.evento"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.evento"/>
					</c:if>
					</li>
				</ol>
			</nav>
		</div>
		</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idEvento"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="tipo"><spring:message code="label.tipo"/></label> <form:input path="tipo" class="form-control"/><form:errors path="tipo" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="descripcion"><spring:message code="label.descripcion"/></label> <form:input path="descripcion" class="form-control"/><form:errors path="descripcion" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="palabrasClave"><spring:message code="label.palabrasClave"/></label> <form:input path="palabrasClave" class="form-control"/><form:errors path="palabrasClave" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="observaciones"><spring:message code="label.observaciones"/></label> <form:input path="observaciones" class="form-control"/><form:errors path="observaciones" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="fecha"><spring:message code="label.fecha"/></label> <form:input path="fecha" class="form-control"/><form:errors path="fecha" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idProyecto"><spring:message code="label.idProyecto"/></label> <form:input path="idProyecto" class="form-control"/><form:errors path="idProyecto" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoEventos1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

