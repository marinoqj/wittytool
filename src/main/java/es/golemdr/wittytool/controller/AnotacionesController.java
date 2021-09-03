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
import es.golemdr.wittytool.domain.Anotacion;
import es.golemdr.wittytool.domain.form.AnotacionForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.AnotacionesService;



@Controller
public class AnotacionesController {

	private static Logger log = LogManager.getLogger(AnotacionesController.class);

	private static final String ANOTACIONES = "anotaciones";
	private static final String ANOTACION = "anotacion";

	@Autowired
	private AnotacionesService anotacionesService;



	@GetMapping(value=UrlConstants.URL_LISTADO_ANOTACIONES)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Anotacion> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = anotacionesService.getAnotaciones(paginacion);

		map.put("paginacion", paginacion);
		map.put(ANOTACIONES, resultado);
		map.put(ANOTACION,new AnotacionForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_ANOTACIONES;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_ANOTACION)
	public String insertar(AnotacionForm formulario, Model model) {

		Anotacion entity = new Anotacion();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		anotacionesService.insertarActualizarAnotacion(entity);

		return ForwardConstants.RED_LISTADO_ANOTACIONES; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_ANOTACION)
	public String editar(String idAnotacion, Map<String, Object> map) {

		Anotacion entity = anotacionesService.getById(Long.valueOf(idAnotacion));

		AnotacionForm formulario = new AnotacionForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(ANOTACION,formulario);

		return ForwardConstants.FWD_ANOTACION_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_ANOTACION)
	public String actualizar(@Valid AnotacionForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_ANOTACION_FORM;

		}else{

			Anotacion entity = new Anotacion();

			try {

				BeanUtils.copyProperties(entity, formulario);

				anotacionesService.insertarActualizarAnotacion(entity);

				destino = ForwardConstants.RED_LISTADO_ANOTACIONES;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_ANOTACION)
	public String borrar(String idAnotacion, Map<String, Object> map) {

		anotacionesService.borrarAnotacion(Long.valueOf(idAnotacion));

		return ForwardConstants.RED_LISTADO_ANOTACIONES;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_ANOTACIONES)
	public String buscarAnotaciones(AnotacionForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Anotacion> resultado = null;

		Anotacion entity = new Anotacion();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = anotacionesService.findAnotacionesByExample(entity, paginacion);
			total = anotacionesService.countAnotacionesByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(ANOTACIONES, resultado);
		map.put(ANOTACION,new AnotacionForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_ANOTACIONES;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_ANOTACIONES_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Anotacion> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Anotacion anotacion = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Anotacion){

			anotacion = (Anotacion)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = anotacionesService.findAnotacionesByExample(anotacion, paginacion);
		total = anotacionesService.countAnotacionesByExample(anotacion);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(ANOTACION, new AnotacionForm());
		model.addAttribute(ANOTACIONES, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_ANOTACIONES;
	}



}

