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
import es.golemdr.wittytool.domain.Incidencia;
import es.golemdr.wittytool.domain.form.IncidenciaForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.IncidenciasService;



@Controller
public class IncidenciasController {

	private static Logger log = LogManager.getLogger(IncidenciasController.class);

	private static final String INCIDENCIAS = "incidencias";
	private static final String INCIDENCIA = "incidencia";

	@Autowired
	private IncidenciasService incidenciasService;



	@GetMapping(value=UrlConstants.URL_LISTADO_INCIDENCIAS)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Incidencia> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = incidenciasService.getIncidencias(paginacion);

		map.put("paginacion", paginacion);
		map.put(INCIDENCIAS, resultado);
		map.put(INCIDENCIA,new IncidenciaForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_INCIDENCIAS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_INCIDENCIA)
	public String insertar(IncidenciaForm formulario, Model model) {

		Incidencia entity = new Incidencia();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		incidenciasService.insertarActualizarIncidencia(entity);

		return ForwardConstants.RED_LISTADO_INCIDENCIAS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_INCIDENCIA)
	public String editar(String idIncidencia, Map<String, Object> map) {

		Incidencia entity = incidenciasService.getById(Long.valueOf(idIncidencia));

		IncidenciaForm formulario = new IncidenciaForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(INCIDENCIA,formulario);

		return ForwardConstants.FWD_INCIDENCIA_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_INCIDENCIA)
	public String actualizar(@Valid IncidenciaForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_INCIDENCIA_FORM;

		}else{

			Incidencia entity = new Incidencia();

			try {

				BeanUtils.copyProperties(entity, formulario);

				incidenciasService.insertarActualizarIncidencia(entity);

				destino = ForwardConstants.RED_LISTADO_INCIDENCIAS;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_INCIDENCIA)
	public String borrar(String idIncidencia, Map<String, Object> map) {

		incidenciasService.borrarIncidencia(Long.valueOf(idIncidencia));

		return ForwardConstants.RED_LISTADO_INCIDENCIAS;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_INCIDENCIAS)
	public String buscarIncidencias(IncidenciaForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Incidencia> resultado = null;

		Incidencia entity = new Incidencia();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = incidenciasService.findIncidenciasByExample(entity, paginacion);
			total = incidenciasService.countIncidenciasByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(INCIDENCIAS, resultado);
		map.put(INCIDENCIA,new IncidenciaForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_INCIDENCIAS;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_INCIDENCIAS_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Incidencia> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Incidencia incidencia = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Incidencia){

			incidencia = (Incidencia)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = incidenciasService.findIncidenciasByExample(incidencia, paginacion);
		total = incidenciasService.countIncidenciasByExample(incidencia);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(INCIDENCIA, new IncidenciaForm());
		model.addAttribute(INCIDENCIAS, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_INCIDENCIAS;
	}



}

