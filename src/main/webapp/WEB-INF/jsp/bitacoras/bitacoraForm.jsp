
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="bitacora" action='${modo}Bitacora.do' method="post">


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
					<spring:message code="label.alta.bitacora"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.bitacora"/>
					</c:if>
					</li>
				</ol>
			</nav>
		</div>
		</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idBitacora"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idProyecto"><spring:message code="label.idProyecto"/></label> <form:input path="idProyecto" class="form-control"/><form:errors path="idProyecto" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="idUsuario"><spring:message code="label.idUsuario"/></label> <form:input path="idUsuario" class="form-control"/><form:errors path="idUsuario" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoBitacoras1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

