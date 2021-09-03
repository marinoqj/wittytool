<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


<!-- Banner de la página de Login -->

<nav class="navbar navbar-expand-lg navbar-light fixed-top scrolling-navbar" style="background-color: #e3f2fd;">
<div class="container-fluid">
	Aplicación generada
</div>
</nav>			
			
<!-- FIN Menú -->

<br>
<br>
<br>
<br>
<br>
<br>


<div class="row">
	<div class="col-md-3 col-lg-4">&nbsp;</div>
	<div class="col-md-6 col-lg-4">
		
 		<form class="shadow p-3 mb-5 bg-white rounded text-center border" action="login.do" method="post">	
 		<div class="p-4">

			<span class="h4 mb-4">Acceso a la aplicación</span>
			<br><br>
			<img src='<spring:url value="/static/imagenes/user-circle-solid.png"/>' height="100" width="100">
			<br><br>
			<!-- Name -->
			<input type="text" class="form-control mb-4" name="username" placeholder="Usuario">

			<!-- Password -->
			<input type="password" name="password" class="form-control mb-4" placeholder="Password">

			<!-- Sign in button -->
			<button class="btn btn-primary btn-block btn-sm" type="submit">Entrar</button>

		</div>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		
	</div>
	<div class="col-md-3 col-lg-4">&nbsp;</div>


</div>

<div class="row">
	<div class="col-md-3 col-lg-4">&nbsp;</div>
	<div class="col-md-6 col-lg-4 align-middle">usuario -> abc  // admin --> 123</div>
	<div class="col-md-3 col-lg-4">&nbsp;</div>
</div>

<br>

<c:if test="${not empty mensaje}">
<div class="row">
	<div class="col-md-3 col-lg-4">&nbsp;</div>
	<div class="col-md-6 col-lg-4 align-middle alert alert-danger"><div id="rojo"><span><i class="fas fa-info-circle fa-2x"></i> ${mensaje}</div></div>
	<div class="col-md-3 col-lg-4">&nbsp;</div>
</div>
</c:if>


<br>
<br>
<br>
<br>