<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>

 
 
			<!-- Menú -->
			
			<nav class="navbar navbar-expand-lg navbar-light fixed-top scrolling-navbar" style="background-color: #a8e9ff;">
			<div class="container-fluid">
			  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			    <span class="navbar-toggler-icon"></span>
			  </button>
			
			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			    <ul class="navbar-nav mr-auto">
			      <li class="nav-item active">
			        <a class="nav-link" href='<spring:url value="/inicio.do"/>'>Inicio <span class="sr-only">(current)</span></a>
			      </li>
			    </ul>
			    <ul class="navbar-nav">
			      <li class="nav-item dropdown active">
			        <a class="nav-link dropdown-toggle"  href="#" id="navbarDropdownMenuLink-333" data-toggle="dropdown"
			          aria-haspopup="true" aria-expanded="false">
			          Margarita Salas &nbsp;&nbsp;&nbsp;&nbsp;<i class="fas fa-user-circle fa-lg"></i>
			        </a>
			        <div class="dropdown-menu dropdown-menu-right dropdown-default"
			          aria-labelledby="navbarDropdownMenuLink-333">
			          <a class="dropdown-item" href="#">Mi perfil</a>
			          <a class="dropdown-item" href="#">Cerrar sesión</a>
			        </div>
			      </li>
			    </ul>
			  </div>
			  </div>
			</nav>			
						
			<!-- FIN Menú -->



