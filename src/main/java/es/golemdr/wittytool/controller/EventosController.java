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
import es.golemdr.wittytool.domain.Evento;
import es.golemdr.wittytool.domain.form.EventoForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.EventosService;



@Controller
public class EventosController {

	private static Logger log = LogManager.getLogger(EventosController.class);

	private static final String EVENTOS = "eventos";
	private static final String EVENTO = "evento";

	@Autowired
	private EventosService eventosService;



	@GetMapping(value=UrlConstants.URL_LISTADO_EVENTOS)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Evento> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = eventosService.getEventos(paginacion);

		map.put("paginacion", paginacion);
		map.put(EVENTOS, resultado);
		map.put(EVENTO,new EventoForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_EVENTOS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_EVENTO)
	public String insertar(EventoForm formulario, Model model) {

		Evento entity = new Evento();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		eventosService.insertarActualizarEvento(entity);

		return ForwardConstants.RED_LISTADO_EVENTOS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_EVENTO)
	public String editar(String idEvento, Map<String, Object> map) {

		Evento entity = eventosService.getById(Long.valueOf(idEvento));

		EventoForm formulario = new EventoForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(EVENTO,formulario);

		return ForwardConstants.FWD_EVENTO_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_EVENTO)
	public String actualizar(@Valid EventoForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_EVENTO_FORM;

		}else{

			Evento entity = new Evento();

			try {

				BeanUtils.copyProperties(entity, formulario);

				eventosService.insertarActualizarEvento(entity);

				destino = ForwardConstants.RED_LISTADO_EVENTOS;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_EVENTO)
	public String borrar(String idEvento, Map<String, Object> map) {

		eventosService.borrarEvento(Long.valueOf(idEvento));

		return ForwardConstants.RED_LISTADO_EVENTOS;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_EVENTOS)
	public String buscarEventos(EventoForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Evento> resultado = null;

		Evento entity = new Evento();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = eventosService.findEventosByExample(entity, paginacion);
			total = eventosService.countEventosByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(EVENTOS, resultado);
		map.put(EVENTO,new EventoForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_EVENTOS;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_EVENTOS_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Evento> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Evento evento = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Evento){

			evento = (Evento)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = eventosService.findEventosByExample(evento, paginacion);
		total = eventosService.countEventosByExample(evento);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(EVENTO, new EventoForm());
		model.addAttribute(EVENTOS, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_EVENTOS;
	}



}

