
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="constanteauxiliar" action='${modo}ConstanteAuxiliar.do' method="post">


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
					<spring:message code="label.alta.constanteauxiliar"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.constanteauxiliar"/>
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
										<label for="activa"><spring:message code="label.activa"/></label> <form:input path="activa" class="form-control"/><form:errors path="activa" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoConstantesAuxiliares1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

