
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


	<form:form modelAttribute="usuario" action='${modo}Usuario.do' method="post">


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
					<spring:message code="label.alta.usuario"/>
					</c:if>
					<c:if test="${modo == 'actualizar'}">
					<spring:message code="label.editar.usuario"/>
					</c:if>
					</li>
				</ol>
			</nav>
		</div>
		</div>

						

							<c:if test="${modo == 'actualizar'}">

								<form:hidden path="idUsuario"/>

							</c:if>
							

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="login"><spring:message code="label.login"/></label> <form:input path="login" class="form-control"/><form:errors path="login" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="password"><spring:message code="label.password"/></label> <form:input path="password" class="form-control"/><form:errors path="password" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="nombre"><spring:message code="label.nombre"/></label> <form:input path="nombre" class="form-control"/><form:errors path="nombre" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="apellido1"><spring:message code="label.apellido1"/></label> <form:input path="apellido1" class="form-control"/><form:errors path="apellido1" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="apellido2"><spring:message code="label.apellido2"/></label> <form:input path="apellido2" class="form-control"/><form:errors path="apellido2" element="div" id="rojo"/>
									</div>
								</div>
					

								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="cambiarPassword"><spring:message code="label.cambiarPassword"/></label> <form:input path="cambiarPassword" class="form-control"/><form:errors path="cambiarPassword" element="div" id="rojo"/>
									</div>
								</div>
					


							<a href="./listadoUsuarios1.do" class="btn btn-secondary btn-sm"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</a>
							<button type="submit" class="btn btn-success btn-sm" ><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>



	</div>
	</div>

	</form:form>

