<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<%@ tag body-content="empty" %>
<%@ attribute name="accion" required="true" %>	



  <ul class="pagination pagination-sm pg-blue justify-content-end">
	<c:if test="${(paginacion.totalPaginas) > 1}">
			<c:if test="${paginacion.inicio == 0}">	
			    <li class="page-item disabled"><a class="page-link">Primero</a></li>
			    <li class="page-item disabled">
			      <a class="page-link" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			        <span class="sr-only">Previous</span>
			      </a>
			    </li>				
			</c:if>
			<c:if test="${paginacion.inicio > 0}">
			    <li class="page-item"><a href='<c:out value="${accion}"/>1.do' class="page-link">Primero</a></li>
			    <li class="page-item">
			      <a href='<c:out value="${accion}"/><c:out value="${paginacion.inicio}"/>.do' class="page-link" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			        <span class="sr-only">Previous</span>
			      </a>
			    </li>			
			</c:if>
				<li class="page-item active"><a class="page-link"><c:out value="${paginacion.inicio + 1}"/></a></li>
			<c:if test="${paginacion.inicio + 1 < paginacion.totalPaginas}">
				<li class="page-item">
			      <a href='<c:out value="${accion}"/><c:out value="${paginacion.inicio + 2}"/>.do' class="page-link" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			        <span class="sr-only">Next</span>
			      </a>
			    </li>		    
			    <li class="page-item"><a href='<c:out value="${accion}"/><c:out value="${paginacion.totalPaginas}"/>.do' class="page-link">Último</a></li>
			</c:if>
			<c:if test="${paginacion.inicio + 1 == paginacion.totalPaginas}">
				<li class="page-item disabled">
			      <a class="page-link" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			        <span class="sr-only">Next</span>
			      </a>
			    </li>
			    <li class="page-item disabled"><a class="page-link">Último</a></li>			
			</c:if>
			<li class="page-item"><a class="page-link">(<c:out value="${paginacion.totalPaginas}"/> págs.)  [<c:out value="${paginacion.totalRegistros}"/>]</a></li>		
	</c:if>			

  </ul>
