<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp"%>

<script>

$(document).ready(function(){
    $(".selector").click(function(){       	
    var idCapa = $(this).data('id');          
        $("#" + idCapa).toggle();
        
        return false;
    });
});

</script>

<br>
<br>
<br>
<br>
<br>
	<div class="row">
	  <div class="col-2">&nbsp;</div>
	  <div class="col-8">
	  			<div class="alert alert-danger">									
					<span><i class="fas fa-info-circle fa-2x"></i></span>&nbsp;&nbsp;&nbsp;<b><spring:message code="error_aplicacion" />:</b>
					<spring:message code="${mensaje}" />
					<br>
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Si el error persiste contacte con el administrador. 
					<br>
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Código de error : ${codigo}</b>
				</div>					

	  </div>
	  <div class="col-2">&nbsp;</div>
    </div>	

<br>

<div class="row">
	<div class="col-2">&nbsp;</div>
	<div class="col-8"><a href="#" class="selector" data-id="detalleTraza">Ver detalle</a></div>
	<div class="col-2">&nbsp;</div>
</div>
<div class="row">	
		<div class="col-2">&nbsp;</div>
		<div class="col-8"> 
			<div id="detalleTraza" style="display:none;">
				${traza}
			</div>
		</div>			
		<div class="col-2">&nbsp;</div>
</div>	