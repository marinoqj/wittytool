package es.golemdr.wittytool.controller;



import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.golemdr.wittytool.ext.Constantes;
import es.golemdr.wittytool.controller.constantes.ForwardConstants;
import es.golemdr.wittytool.controller.constantes.UrlConstants;
import es.golemdr.wittytool.domain.Rol;
import es.golemdr.wittytool.domain.form.RolForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.RolesService;



@Controller
public class RolesController {

	private static Logger log = LogManager.getLogger(RolesController.class);

	private static final String ROLES = "roles";
	private static final String ROL = "rol";

	@Autowired
	private RolesService rolesService;



	@GetMapping(value=UrlConstants.URL_LISTADO_ROLES)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Rol> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = rolesService.getRoles(paginacion);

		map.put("paginacion", paginacion);
		map.put(ROLES, resultado);
		map.put(ROL,new RolForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_ROLES;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_ROL)
	public String insertar(RolForm formulario, Model model) {

		Rol entity = new Rol();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		rolesService.insertarActualizarRol(entity);

		return ForwardConstants.RED_LISTADO_ROLES; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_ROL)
	public String editar(String idRol, Map<String, Object> map) {

		Rol entity = rolesService.getById(Long.valueOf(idRol));

		RolForm formulario = new RolForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(ROL,formulario);

		return ForwardConstants.FWD_ROL_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_ROL)
	public String actualizar(@Valid RolForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_ROL_FORM;

		}else{

			Rol entity = new Rol();

			try {

				BeanUtils.copyProperties(entity, formulario);

				rolesService.insertarActualizarRol(entity);

				destino = ForwardConstants.RED_LISTADO_ROLES;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_ROL)
	public String borrar(String idRol, Map<String, Object> map) {

		rolesService.borrarRol(Long.valueOf(idRol));

		return ForwardConstants.RED_LISTADO_ROLES;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_ROLES)
	public String buscarRoles(RolForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Rol> resultado = null;

		Rol entity = new Rol();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = rolesService.findRolesByExample(entity, paginacion);
			total = rolesService.countRolesByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(ROLES, resultado);
		map.put(ROL,new RolForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_ROLES;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_ROLES_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Rol> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Rol rol = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Rol){

			rol = (Rol)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = rolesService.findRolesByExample(rol, paginacion);
		total = rolesService.countRolesByExample(rol);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(ROL, new RolForm());
		model.addAttribute(ROLES, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_ROLES;
	}



}

