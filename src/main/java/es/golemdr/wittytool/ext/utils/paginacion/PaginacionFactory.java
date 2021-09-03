package es.golemdr.wittytool.ext.utils.paginacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class PaginacionFactory {
	
	private PaginacionFactory(){}

	
	public static PaginacionBean getPaginacionBean(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		PaginacionBean paginacion = (PaginacionBean)session.getAttribute("paginacion");
		
		
		if(paginacion == null){
			paginacion = new PaginacionBean();
			session.setAttribute("paginacion",paginacion);
		}
		
		return paginacion;
		
	}

	
	public static void setElementosXPagina(int elementosXpagina,HttpServletRequest request ){
		
		PaginacionBean paginacion = getPaginacionBean(request);
		
		paginacion.setElementosXpagina(elementosXpagina);
		
	}
	
}
