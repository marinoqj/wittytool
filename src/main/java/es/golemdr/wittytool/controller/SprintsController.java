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
import es.golemdr.wittytool.domain.Sprint;
import es.golemdr.wittytool.domain.form.SprintForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.SprintsService;



@Controller
public class SprintsController {

	private static Logger log = LogManager.getLogger(SprintsController.class);

	private static final String SPRINTS = "sprints";
	private static final String SPRINT = "sprint";

	@Autowired
	private SprintsService sprintsService;



	@GetMapping(value=UrlConstants.URL_LISTADO_SPRINTS)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Sprint> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = sprintsService.getSprints(paginacion);

		map.put("paginacion", paginacion);
		map.put(SPRINTS, resultado);
		map.put(SPRINT,new SprintForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_SPRINTS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_SPRINT)
	public String insertar(SprintForm formulario, Model model) {

		Sprint entity = new Sprint();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		sprintsService.insertarActualizarSprint(entity);

		return ForwardConstants.RED_LISTADO_SPRINTS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_SPRINT)
	public String editar(String idSprint, Map<String, Object> map) {

		Sprint entity = sprintsService.getById(Long.valueOf(idSprint));

		SprintForm formulario = new SprintForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(SPRINT,formulario);

		return ForwardConstants.FWD_SPRINT_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_SPRINT)
	public String actualizar(@Valid SprintForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_SPRINT_FORM;

		}else{

			Sprint entity = new Sprint();

			try {

				BeanUtils.copyProperties(entity, formulario);

				sprintsService.insertarActualizarSprint(entity);

				destino = ForwardConstants.RED_LISTADO_SPRINTS;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_SPRINT)
	public String borrar(String idSprint, Map<String, Object> map) {

		sprintsService.borrarSprint(Long.valueOf(idSprint));

		return ForwardConstants.RED_LISTADO_SPRINTS;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_SPRINTS)
	public String buscarSprints(SprintForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Sprint> resultado = null;

		Sprint entity = new Sprint();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = sprintsService.findSprintsByExample(entity, paginacion);
			total = sprintsService.countSprintsByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(SPRINTS, resultado);
		map.put(SPRINT,new SprintForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_SPRINTS;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_SPRINTS_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Sprint> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Sprint sprint = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Sprint){

			sprint = (Sprint)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = sprintsService.findSprintsByExample(sprint, paginacion);
		total = sprintsService.countSprintsByExample(sprint);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(SPRINT, new SprintForm());
		model.addAttribute(SPRINTS, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_SPRINTS;
	}



}

