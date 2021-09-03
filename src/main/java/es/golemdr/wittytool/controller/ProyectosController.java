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
import es.golemdr.wittytool.domain.Proyecto;
import es.golemdr.wittytool.domain.form.ProyectoForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.ProyectosService;



@Controller
public class ProyectosController {

	private static Logger log = LogManager.getLogger(ProyectosController.class);

	private static final String PROYECTOS = "proyectos";
	private static final String PROYECTO = "proyecto";

	@Autowired
	private ProyectosService proyectosService;



	@GetMapping(value=UrlConstants.URL_LISTADO_PROYECTOS)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Proyecto> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = proyectosService.getProyectos(paginacion);

		map.put("paginacion", paginacion);
		map.put(PROYECTOS, resultado);
		map.put(PROYECTO,new ProyectoForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PROYECTOS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_PROYECTO)
	public String insertar(ProyectoForm formulario, Model model) {

		Proyecto entity = new Proyecto();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		proyectosService.insertarActualizarProyecto(entity);

		return ForwardConstants.RED_LISTADO_PROYECTOS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_PROYECTO)
	public String editar(String idProyecto, Map<String, Object> map) {

		Proyecto entity = proyectosService.getById(Long.valueOf(idProyecto));

		ProyectoForm formulario = new ProyectoForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(PROYECTO,formulario);

		return ForwardConstants.FWD_PROYECTO_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_PROYECTO)
	public String actualizar(@Valid ProyectoForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_PROYECTO_FORM;

		}else{

			Proyecto entity = new Proyecto();

			try {

				BeanUtils.copyProperties(entity, formulario);

				proyectosService.insertarActualizarProyecto(entity);

				destino = ForwardConstants.RED_LISTADO_PROYECTOS;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_PROYECTO)
	public String borrar(String idProyecto, Map<String, Object> map) {

		proyectosService.borrarProyecto(Long.valueOf(idProyecto));

		return ForwardConstants.RED_LISTADO_PROYECTOS;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_PROYECTOS)
	public String buscarProyectos(ProyectoForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Proyecto> resultado = null;

		Proyecto entity = new Proyecto();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = proyectosService.findProyectosByExample(entity, paginacion);
			total = proyectosService.countProyectosByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(PROYECTOS, resultado);
		map.put(PROYECTO,new ProyectoForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PROYECTOS;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_PROYECTOS_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Proyecto> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Proyecto proyecto = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Proyecto){

			proyecto = (Proyecto)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = proyectosService.findProyectosByExample(proyecto, paginacion);
		total = proyectosService.countProyectosByExample(proyecto);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(PROYECTO, new ProyectoForm());
		model.addAttribute(PROYECTOS, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_PROYECTOS;
	}



}

