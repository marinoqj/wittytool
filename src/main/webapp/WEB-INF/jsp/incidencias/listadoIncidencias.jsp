

<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>

<script>
function borrarIncidencia(idIncidencia){

	 	document.formulario.idIncidencia.value = idIncidencia;
	 	document.formulario.action="borrarIncidencia.do";
	 	document.formulario.submit();

}
function editarIncidencia(idIncidencia){

 	document.formulario.idIncidencia.value = idIncidencia;
 	document.formulario.action="editarIncidencia.do";
 	document.formulario.submit();


}

function mostarConfirmBorrarIncidencia(idIncidencia) {
    var message = '<spring:message code="confirmacion.borrar.incidencia"/>';

    $("#aceptarBorrar").attr("href", "javascript:borrarIncidencia(" + idIncidencia + ");");

    mostrarConfirm(message);
}


function mostrarConfirm(message) {
    $('#alertModal').find('.modal-body p').html(message);
    $('#alertModal').modal('show');
}

$(document).ready(function(){
    $('#ventanaBuscar').on('hidden.bs.modal', function () {
        $('form[id="formularioBuscar"]').trigger('reset');
    });
});


</script>


<!-- Warning Modal -->
<div id="alertModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
         <p></p>
      </div>
      <div class="modal-footer">
         <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</button>
         <a class="btn btn-primary btn-sm" role="button" href="#" id="aceptarBorrar"><i class="fas fa-check-circle"></i>&nbsp;&nbsp;<spring:message code="button.aceptar" /></a>
      </div>
    </div>
  </div>
</div>

<!-- ./ Warning Modal -->

<form name="formulario" method="post">
	<input type="hidden" name="idIncidencia"/>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

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
							class="fas fa-users fa-lg mr-2"></i>Listado de incidencias</li>
					</ol>
				</nav>
			</div>
		</div>



<c:if  test="${!empty incidencias}">


<div class="row">
	<div class="col-md-8"></div>

	<c:if test="${hayFiltro eq false}">
		<mistags:paginacion accion="listadoIncidencias" />
	</c:if>

	<c:if test="${hayFiltro eq true}">
		<mistags:paginacion accion="listadoIncidenciasFiltrado" />
	</c:if>
</div>


<br>

	<table class="table table-hover">
		<thead class="blue lighten-4">
			<tr class="bg-light">
	    		
					<th scope="col"><spring:message code="label.tipo"/></th>
					
					<th scope="col"><spring:message code="label.resumen"/></th>
					
					<th scope="col"><spring:message code="label.descripcion"/></th>
					
					<th scope="col"><spring:message code="label.prioridad"/></th>
					
					<th scope="col"><spring:message code="label.idSprint"/></th>
					
					<th scope="col"><spring:message code="label.idProyecto"/></th>
					
					<th scope="col">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
		<c:forEach items="${incidencias}" var="incidencia">
			<tr>
			
				<td>${incidencia.tipo}</td>
			
				<td>${incidencia.resumen}</td>
			
				<td>${incidencia.descripcion}</td>
			
				<td>${incidencia.prioridad}</td>
			
				<td>${incidencia.idSprint}</td>
			
				<td>${incidencia.idProyecto}</td>
			
				<td>
					<div>
							<label data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> <span style="cursor: pointer;"><i
									class="fas fa-cog fa-lg"></i></span>
							</label>
							<ul class="dropdown-menu dropdown-primary">
								<li class="nav-item dropdown"><a class="dropdown-item"
									href="javascript:editarIncidencia('${incidencia.idIncidencia}')"><spring:message code="label.editar"/></a></li>
								<li class="nav-item dropdown"><a class="dropdown-item"
									href="javascript:mostarConfirmBorrarIncidencia('${incidencia.idIncidencia}')"><spring:message code="label.borrar"/></a></li>
							</ul>
						</div>
				</td>
			</tr>
		</c:forEach>
			</tbody>
			</table>


		</c:if>

		<c:if  test="${empty incidencias}">

			<br>
			<br>
			<br>
			<br>
			<div class="text-center">No hay incidencias que mostrar...</div>
		</c:if>

		<div class="row">
			<div class="col-md-12">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#nuevoFormulario"><i class="fas fa-plus-circle"></i>&nbsp;&nbsp;Nuevo</button>
				<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#ventanaBuscar"><i class="fas fa-search"></i> &nbsp;&nbsp;Buscar</button>
			</div>
		</div>


<!-- Modal Nuev@ incidencia-->
<div class="modal fade" id="nuevoFormulario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0" style="background-color: #e9ecef; color: #6c757d">
        <span class="modal-title" id="exampleModalLabel"><i class="fas fa-plus-circle fa-lg pr-1"></i><i class="fas fa-cube fa-lg pr-2"></i>Nueva incidencia</span>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="color: #6c757d">
		<form:form modelAttribute="incidencia" action="insertarIncidencia.do" method="post">

    		


					<div class="form-group">
						<label for="tipo"><spring:message code="label.tipo"/></label> <form:input path="tipo" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="resumen"><spring:message code="label.resumen"/></label> <form:input path="resumen" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="descripcion"><spring:message code="label.descripcion"/></label> <form:input path="descripcion" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="prioridad"><spring:message code="label.prioridad"/></label> <form:input path="prioridad" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="idSprint"><spring:message code="label.idSprint"/></label> <form:input path="idSprint" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="idProyecto"><spring:message code="label.idProyecto"/></label> <form:input path="idProyecto" class="form-control"/>
					</div>

					

			<br>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</button>
        <button type="submit" class="btn btn-success btn-sm"><i class="fas fa-save"></i> &nbsp;&nbsp;Guardar</button>
      </div>

      </form:form>
    </div>
  </div>
</div>
</div>


<!-- Modal Busqueda -->
<div class="modal fade" id="ventanaBuscar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0" style="background-color: #e9ecef; color: #6c757d">
        <span class="modal-title" id="exampleModalLabel"><i class="fas fas fa-search fa-lg pr-1"></i><i class="fas fa-cube fa-lg pr-2"></i>Buscar Constante</span>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="color: #6c757d">
		<form:form modelAttribute="incidencia" action='buscarIncidencias.do' method="post" id="formularioBuscar">

    		


					<div class="form-group">
						<label for="tipo"><spring:message code="label.tipo"/></label> <form:input path="tipo" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="resumen"><spring:message code="label.resumen"/></label> <form:input path="resumen" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="descripcion"><spring:message code="label.descripcion"/></label> <form:input path="descripcion" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="prioridad"><spring:message code="label.prioridad"/></label> <form:input path="prioridad" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="idSprint"><spring:message code="label.idSprint"/></label> <form:input path="idSprint" class="form-control"/>
					</div>

					


					<div class="form-group">
						<label for="idProyecto"><spring:message code="label.idProyecto"/></label> <form:input path="idProyecto" class="form-control"/>
					</div>

					


			<br>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal"><i class="fas fa-times-circle"></i>&nbsp;&nbsp;Cancelar</button>
        <button type="submit" class="btn btn-success btn-sm"><i class="fas fa-save"></i> &nbsp;&nbsp;Buscar</button>
      </div>

      </form:form>
    </div>
  </div>
</div>
</div>


